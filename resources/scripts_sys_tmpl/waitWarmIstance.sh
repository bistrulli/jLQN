#!/bin/bash

# --- Configurazione ---
# URL del tuo server Prometheus
PROMETHEUS_URL="http://localhost:9090" # <-- MODIFICA QUESTO URL!

# La query da eseguire periodicamente
# Questa somma il numero totale di istanze attive su tutte le serie temporali della metrica
# Se vuoi filtrare per un servizio specifico, modifica la query aggiungendo le etichette:
# es: QUERY='sum(stackdriver_cloud_run_revision_run_googleapis_com_container_instance_count{service_name="il-tuo-servizio"})'
QUERY='sum(stackdriver_cloud_run_revision_run_googleapis_com_container_instance_count)'

# Intervallo di tempo tra un controllo e il successivo (in secondi)
CHECK_INTERVAL_SECONDS=60 # Controlla ogni minuto

# --- Funzione di monitoraggio ---

# Funzione che esegue la query e attende finché il risultato non è 0
check_instance_count_until_zero() {
    echo "Inizio monitoraggio conteggio istanze Cloud Run. Controllo ogni ${CHECK_INTERVAL_SECONDS} secondi."
    echo "Query utilizzata: $QUERY"
    echo "URL Prometheus: $PROMETHEUS_URL"
    echo "--------------------------------------------------"

    while true; do
        TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")

        # Esegui la query verso l'API di Prometheus usando curl
        # Codifico la query per l'URL
        ENCODED_QUERY=$(echo "$QUERY" | jq -sRr @uri)
        API_URL="${PROMETHEUS_URL}/api/v1/query?query=${ENCODED_QUERY}"

        echo "[$TIMESTAMP] Eseguo richiesta a: $API_URL"

        # Cattura la risposta e gli errori di curl
        response=$(curl -sS "${API_URL}" 2>&1)
        curl_exit_code=$?

        if [ $curl_exit_code -ne 0 ]; then
            echo "[$TIMESTAMP] ERRORE: curl ha fallito con codice $curl_exit_code. Errore: $response"
            echo "[$TIMESTAMP] Attendo ${CHECK_INTERVAL_SECONDS} secondi prima di riprovare..."
            sleep "$CHECK_INTERVAL_SECONDS"
            continue # Passa all'iterazione successiva del loop
        fi

        # Parsa la risposta JSON usando jq
        # Verifico che lo status sia "success" e che ci sia almeno un risultato
        status=$(echo "$response" | jq -r '.status')
        result_length=$(echo "$response" | jq '.data.result | length')

        if [ "$status" != "success" ]; then
             echo "[$TIMESTAMP] ERRORE API: Lo status nella risposta non è 'success'. Status ricevuto: '$status'. Risposta completa: $response"
             echo "[$TIMESTAMP] Attendo ${CHECK_INTERVAL_SECONDS} secondi prima di riprovare..."
             sleep "$CHECK_INTERVAL_SECONDS"
             continue
        elif [ "$result_length" -eq 0 ]; then
            # Questo caso si verifica se la query non trova serie temporali o se la condizione `== 0`
            # viene usata nella query stessa e non è vera. Ma noi usiamo sum() e controlliamo dopo.
            # Per sum() su una metrica esistente, result_length dovrebbe essere sempre 1.
            # Se è 0, potrebbe indicare che la metrica non esiste o non ha dati nell'intervallo.
            echo "[$TIMESTAMP] ATTENZIONE: La query ha restituito un risultato vuoto (length = 0). La metrica è presente e ha dati?"
            # Potrebbe significare che il valore è 0, ma è più sicuro basarsi sul parsing del valore esplicito
            # Tuttavia, se non ci sono dati, non ci sono istanze -> potremmo considerarlo 0.
            # Per questo script, procediamo e estraiamo il valore; l'errore di jq dopo gestirà il caso.
             instance_count="ERRORE_JQ" # Segnalo che l'estrazione fallirà
        else
            # Estrai il valore del conteggio delle istanze
            # .data.result[0].value[1] -> accede al primo risultato, al campo value, e al secondo elemento (il valore)
            instance_count=$(echo "$response" | jq -r '.data.result[0].value[1]')

            # jq potrebbe fallire se il JSON non è come previsto anche con length > 0 (improbabile per API standard)
            if [ $? -ne 0 ]; then
                echo "[$TIMESTAMP] ERRORE JQ: Impossibile estrarre il valore dal JSON. Risposta: $response"
                instance_count="ERRORE_JQ_PARSE"
            fi
        fi

        # --- Logga e Controlla il Valore ---

        if [ "$instance_count" = "ERRORE_JQ" ] || [ "$instance_count" = "ERRORE_JQ_PARSE" ]; then
             # Già loggato l'errore di parsing o risultato vuoto, aspetta e riprova
             echo "[$TIMESTAMP] Errore nell'ottenere il conteggio. Attendo ${CHECK_INTERVAL_SECONDS} secondi per riprovare..."
        elif [ "$instance_count" = "0" ]; then
            echo "[$TIMESTAMP] RILEVATO: Il numero totale di istanze attive è 0."
            echo "Monitoraggio terminato."
            return 0 # Esce dalla funzione con codice 0 (successo)
        else
            echo "[$TIMESTAMP] Conteggio istanze attive: $instance_count (non zero)."
            echo "Attendo ${CHECK_INTERVAL_SECONDS} secondi per il prossimo controllo..."
        fi

        # Attendi l'intervallo prima del prossimo controllo
        sleep "$CHECK_INTERVAL_SECONDS"
    done
}

# --- Esegui la funzione ---
check_instance_count_until_zero

# Il codice qui sotto viene eseguito solo dopo che la funzione check_instance_count_until_zero è terminata.
# Puoi aggiungere qui eventuali azioni finali se necessario.
# echo "Script Bash terminato."