clear;

% Funzione per calcolare la percentuale di miglioramento/degrado
function result = percentageCompare(oldArray, newArray)
    % Converti in array colonna per coerenza
    oldArray = oldArray(:);
    newArray = newArray(:);
    % Ritorna la percentuale di confronto: (nuovo - vecchio) / vecchio * 100, negato come nel codice originale
    result = - (newArray - oldArray) ./ oldArray * 100;
end

% --- Inizio Script Principale ---

experiments_array = ["par", "sync", "async"];

% Definisci i limiti comuni per l'asse Y per ciascun tipo di metrica.
% Questo è FONDAMENTALE per la consistenza visiva tra i subplot.
latency_ylim_left = [50 90];      % Limiti per GCR Latency Improvement
latency_ylim_right = [-25 65];    % Limiti per No Conc Latency Improvement (come da tuo codice originale)

billable_ylim_left = [-1700 -100]; % Limiti per GCR Billable Improvement
billable_ylim_right = [-25 65];   % Limiti per No Conc Billable Improvement (come da tuo codice originale)

% Crea una singola figura (a schermo intero per massimizzare lo spazio)
h_fig = figure('units','normalized','outerposition',[0 0 1 1]);

% Cicla attraverso gli esperimenti per popolare i subplot
for i = 1:length(experiments_array)
    experiment = experiments_array(i);
    
    % Carica i dati per l'esperimento corrente
    % Assicurati che il percorso './data/' sia corretto o che i file siano nel percorso di MATLAB
    res = load(fullfile("data", experiment + "_exp_data_processed.mat")); 

    % --- Calcola i dati per RT (Latenza) ---
    gcrRtImp = percentageCompare(res.gcrRtAvg, res.wlRtAvg);
    ncRtImp = percentageCompare(res.ncRtAvg, res.wlRtAvg);
    
    % --- Subplot per Latenza (Prima riga: Posizioni 1, 2, 3) ---
    subplot_col = i; % Colonna per questo esperimento (1 per 'par', 2 per 'sync', 3 per 'async')
    h_ax_latency = subplot(2, 3, subplot_col); % Crea il subplot nella posizione corretta (Riga 1, Colonna i)
    
    % Asse Y sinistro per l'Improvement GCR Latency
    yyaxis left;
    boxplot([gcrRtImp, nan(size(gcrRtImp))]); % Boxplot A (GCR) sul lato sinistro dell'asse Y
    ylim(latency_ylim_left); % Applica i limiti Y comuni
    yticks(linspace(latency_ylim_left(1), latency_ylim_left(2), 5)); % 5 tick equidistanti
    if subplot_col == 1 % Aggiungi l'etichetta dell'asse Y solo al primo subplot della riga
        ylabel('Latency GCR Imp. (\%)', 'Interpreter', 'latex');
    end
    
    % Asse Y destro per l'Improvement No Conc Latency
    yyaxis right;
    boxplot([nan(size(ncRtImp)), ncRtImp]); % Boxplot B (No Conc) sul lato destro dell'asse Y
    ylim(latency_ylim_right); % Applica i limiti Y comuni (qui fissi come nel tuo codice)
    % Nota: L'etichetta dell'asse Y destro non è aggiunta per mantenere la preferenza dei tuoi grafici originali
    
    % Impostazioni comuni per l'asse X e la griglia per il subplot di Latenza
    set(gca, 'xtick', [1 2], 'xticklabel', {'\textbf{A}', '\textbf{B}'}, 'TickLabelInterpreter', 'latex');
    box on; % Mostra la cornice del boxplot
    grid on; % Mostra la griglia
    ax_current = gca; % Ottieni il handle dell'asse corrente
    ax_current.GridColor = [0 0 0]; % Colore della griglia nero
    ax_current.GridAlpha = 0.6; % Trasparenza della griglia
    ax_current.FontSize = 9; % Dimensione del font per una buona leggibilità nei subplot
    
    % Aggiungi il titolo al subplot di Latenza
    title_text_latency = sprintf('\\textbf{%s - Latency}', upper(experiment));
    title(title_text_latency, 'Interpreter', 'latex');

    % --- Calcola i dati per BILL (Istante Fatturabile) ---
    gcrBillImp = percentageCompare(res.gcrBillAvg, res.wlBillAvg);
    ncBillImp = percentageCompare(res.ncBillAvg, res.wlBillAvg); 

    % --- Subplot per Billable (Seconda riga: Posizioni 4, 5, 6) ---
    subplot_idx_billable = i + 3; % Posizione del subplot (Riga 2, Colonna i)
    h_ax_billable = subplot(2, 3, subplot_idx_billable);
    
    % Asse Y sinistro per l'Improvement GCR Billable
    yyaxis left;
    boxplot([gcrBillImp, nan(size(gcrBillImp))]);
    ylim(billable_ylim_left); % Applica i limiti Y comuni
    yticks(linspace(billable_ylim_left(1), billable_ylim_left(2), 5));
    if subplot_idx_billable == 4 % Aggiungi l'etichetta dell'asse Y solo al primo subplot della riga
        ylabel('Billable GCR Imp. (\%)', 'Interpreter', 'latex');
    end
    
    % Asse Y destro per l'Improvement No Conc Billable
    yyaxis right;
    boxplot([nan(size(ncBillImp)), ncBillImp]); % Usa ncBillImp qui
    ylim(billable_ylim_right); % Applica i limiti Y comuni (qui fissi come nel tuo codice)
    % Nota: L'etichetta dell'asse Y destro non è aggiunta per mantenere la preferenza dei tuoi grafici originali
    
    % Impostazioni comuni per l'asse X e la griglia per il subplot di Billable
    set(gca, 'xtick', [1 2], 'xticklabel', {'\textbf{A}', '\textbf{B}'}, 'TickLabelInterpreter', 'latex');
    box on;
    grid on;
    ax_current = gca;
    ax_current.GridColor = [0 0 0];
    ax_current.GridAlpha = 0.6;
    ax_current.FontSize = 9; % Consistente con gli altri subplot
    
    % Aggiungi il titolo al subplot di Billable
    title_text_billable = sprintf('\\textbf{%s - Billable}', upper(experiment));
    title(title_text_billable, 'Interpreter', 'latex');
end

% --- Regolazioni Finali per l'intera Figura ---
% Aggiungi un titolo generale per l'intera figura (super title)
sgtitle('\textbf{Overall Performance Improvement Comparison}', 'Interpreter', 'latex', 'FontSize', 16);

% Opzionale: Regola lo spazio tra i subplot (potrebbe essere necessario scommentare e calibrare)
% Questa è una soluzione avanzata se lo spazio predefinito non è ideale.
set(h_fig, 'DefaultAxesLooseInset', [0.01, 0.01, 0.01, 0.01]); % Riduci lo spazio extra
% In alternativa, se hai la funzione 'tight_subplot' dal File Exchange, puoi usarla per un controllo più preciso.

% Assicurati che la cartella 'figures' esista prima di salvare
if ~exist('figures', 'dir')
   mkdir('figures')
end

% Salva l'intera figura combinata come un unico file PDF vettoriale di alta qualità
exportgraphics(h_fig, "figures/combined_performance_plots.pdf", 'ContentType', 'vector');

% Chiudi la figura dopo il salvataggio
close(h_fig);


%Run Kolmogorov-Smirnoff test
%[hdef,pdef]=kstest2(res.wlessRtAvg,res.defconcRtAvg,'Alpha',0.05);
%[hno,pno]=kstest2(res.wlessRtAvg,res.noconcRtAvg,'Alpha',0.05);
%[hpp,ppp]=kstest2(res.wlessRtAvg,res.propackRtAvg,'Alpha',0.05);

%prctile(reshape(squeeze(res.optCon(:,:,2)),[1,270]),50)