clear


function result = percentageCompare(oldArray, newArray)
    % Converti in array colonna per coerenza
    oldArray = oldArray(:);
    newArray = newArray(:);
    % Ritorna la percentuale di confronto: (nuovo - vecchio) / vecchio * 100, negato come nel codice originale
    result = - (newArray - oldArray) ./ oldArray * 100;
end


res = load(fullfile("data", "synch_exp_data_processed.mat")); 

gcrRtImp = percentageCompare(res.ncRtAvg, res.wlRtAvg);

median(gcrRtImp)