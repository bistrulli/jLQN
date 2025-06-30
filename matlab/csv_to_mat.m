clear; % Pulisce il workspace

% Nome del file CSV di input
%filename = 'experiment_data.csv';

filename = 'async_exp_data.csv';

% Carica i dati dal file CSV
try
    % Assicurati che l'header sia Experiment,Algorithm,RT,BILL
    % e che i tipi di dato siano corretti: string, string, float, float
    data = readtable(filename, 'Delimiter', ',', 'Format', '%s%s%f%f');
catch ME
    error('Errore nel caricamento del file "%s": %s', filename, ME.message);
end

% Ottieni i nomi degli esperimenti unici
unique_experiments = unique(data.Experiment);

% Verifica il numero di esperimenti. Dovrebbero essere 20.
num_experiments = length(unique_experiments);
if num_experiments ~= 20
    warning('Trovati %d esperimenti unici nel CSV. Previsti 20.', num_experiments);
end

% Inizializza i vettori riga (1x20) per i Response Time
gcrRtAvg = NaN(1, num_experiments); % Inizializza con NaN per facilitare il debugging di dati mancanti
wlRtAvg = NaN(1, num_experiments);
ncRtAvg = NaN(1, num_experiments);

% Inizializza i vettori riga (1x20) per i Billable Instance
gcrBillAvg = NaN(1, num_experiments);
wlBillAvg = NaN(1, num_experiments);
ncBillAvg = NaN(1, num_experiments);

% Mappa i nomi degli esperimenti al loro indice per un facile popolamento dei vettori
% Questo è utile perché unique_experiments ordina i nomi
experiment_map = containers.Map(unique_experiments, 1:num_experiments);

% Itera sulle righe della tabella dei dati
for row_idx = 1:height(data)
    current_experiment = data.Experiment{row_idx};
    current_algorithm = data.Algorithm{row_idx};
    current_rt = data.RT(row_idx);
    current_bill = data.BILL(row_idx);
    
    % Trova l'indice dell'esperimento nel nostro array finale
    exp_idx = experiment_map(current_experiment);
    
    % Popola i vettori in base all'algoritmo
    switch current_algorithm
        case 'GCR'
            gcrRtAvg(exp_idx) = current_rt;
            gcrBillAvg(exp_idx) = current_bill;
        case 'WL'
            wlRtAvg(exp_idx) = current_rt;
            wlBillAvg(exp_idx) = current_bill;
        case 'NC'
            ncRtAvg(exp_idx) = current_rt;
            ncBillAvg(exp_idx) = current_bill;
        otherwise
            warning('Algoritmo sconosciuto "%s" trovato nell''esperimento %s. Saltato.', current_algorithm, current_experiment);
    end
end

% Verifica che tutti i valori siano stati popolati (nessun NaN residuo per gli algoritmi attesi)
if any(isnan(gcrRtAvg)) || any(isnan(wlRtAvg)) || any(isnan(ncRtAvg)) || ...
   any(isnan(gcrBillAvg)) || any(isnan(wlBillAvg)) || any(isnan(ncBillAvg))
    warning('Attenzione: Alcuni valori sono rimasti NaN. Ciò indica dati mancanti per alcuni esperimenti/algoritmi nel CSV.');
    disp('RT GCR mancanti per indici:'); disp(find(isnan(gcrRtAvg)));
    disp('RT WL mancanti per indici:'); disp(find(isnan(wlRtAvg)));
    disp('RT NC mancanti per indici:'); disp(find(isnan(ncRtAvg)));
    disp('BILL GCR mancanti per indici:'); disp(find(isnan(gcrBillAvg)));
    disp('BILL WL mancanti per indici:'); disp(find(isnan(wlBillAvg)));
    disp('BILL NC mancanti per indici:'); disp(find(isnan(ncBillAvg)));
end


% Nome del file .mat di output
output_mat_file = 'async_exp_data_processed.mat';

% Salva le variabili nel file .mat
save(output_mat_file, 'gcrRtAvg', 'wlRtAvg', 'ncRtAvg', 'gcrBillAvg', 'wlBillAvg', 'ncBillAvg');

fprintf('File "%s" generato con successo contenente:\n', output_mat_file);
fprintf('  - gcrRtAvg (1x%d double)\n', num_experiments);
fprintf('  - wlRtAvg (1x%d double)\n', num_experiments);
fprintf('  - ncRtAvg (1x%d double)\n', num_experiments);
fprintf('  - gcrBillAvg (1x%d double)\n', num_experiments);
fprintf('  - wlBillAvg (1x%d double)\n', num_experiments);
fprintf('  - ncBillAvg (1x%d double)\n', num_experiments);

% Puoi caricare questo file nel tuo ambiente MATLAB con:
% load('experiment_data_processed.mat');
% E poi usare le variabili come nel tuo script originale.