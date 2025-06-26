clear


function result = percentageCompare(oldArray, newArray)
    % Convert to column array
    oldArray = oldArray(:);
    newArray = newArray(:);
    % Return percentage comparison
    result = - (newArray - oldArray) ./ oldArray * 100;
end

function result = generateFigure(gcrImp, ncImp, filename, gcr_ylim)
    figure('units','normalized','outerposition',[0 0 1 1]);

    % Left axis
    yyaxis left;
    boxplot([gcrImp, nan(size(gcrImp))]);
    ylabel("%", 'Interpreter', 'latex');
    ylim(gcr_ylim);
    yticks(linspace(gcr_ylim(1), gcr_ylim(2), 5));
    
    %set(gca, 'xtick', [1 2], 'xticklabel', {'\textbf{A}:$\frac{(GCR-WL)*100}{GCR}$', '\textbf{B}:$\frac{(No Conc-WL)*100}{No Conc}$'}, 'TickLabelInterpreter', 'latex');
    %title('Boxplots with Two Y-Axes', 'Interpreter', 'latex');
    box on;
    grid on;
    fontsize(gcf, 96, "pixels");

    % Right axis
    % Lasciamo spazio per i boxplot dell'asse sinistro
    yyaxis right;
    boxplot([nan(size(ncImp)), ncImp]); % Spazio sulla posizione 1, ncImp sulla posizione 2
    ylabel("%", 'Interpreter', 'latex');
    ylim([-25 65])
    % Poiché l'asse X è condiviso, i label per l'asse destro andranno nella seconda posizione
    %set(gca, 'xtick', [1 2], 'xticklabel', {'', ''}, 'TickLabelInterpreter', 'latex');

    set(gca, 'xtick', [1 2], 'xticklabel', {'\textbf{A}', '\textbf{B}'}, 'TickLabelInterpreter', 'latex'); %, {'\textbf{A}:$\frac{(GCR-WL)*100}{GCR}$', '\textbf{B}:$\frac{(No Conc-WL)*100}{No Conc}$'}, 'TickLabelInterpreter', 'latex');
    
    exportgraphics(gcf,"figures/" + filename);
    close;
end

experiments_array = ["par", "sync", "async"];

for i = 1:length(experiments_array)
    experiment = experiments_array(i);
    res = load("./" + experiment + "_exp_data_processed.mat");

    % RT data (Latency)
    gcrRtImp = percentageCompare(res.gcrRtAvg, res.wlRtAvg);
    ncRtImp = percentageCompare(res.ncRtAvg, res.wlRtAvg);
    
    % BILL data (Billable Instances)
    gcrBillImp = percentageCompare(res.gcrBillAvg, res.wlBillAvg);
    ncBillImp = percentageCompare(res.ncBillAvg, res.wlBillAvg);
    
    
    generateFigure(gcrRtImp, ncRtImp, experiment + "_overall_latency.pdf", [50 90])
    generateFigure(gcrBillImp, ncBillImp, experiment + "_overall_billable.pdf", [-1700 -100])
end



%Run Kolmogorov-Smirnoff test
%[hdef,pdef]=kstest2(res.wlessRtAvg,res.defconcRtAvg,'Alpha',0.05);
%[hno,pno]=kstest2(res.wlessRtAvg,res.noconcRtAvg,'Alpha',0.05);
%[hpp,ppp]=kstest2(res.wlessRtAvg,res.propackRtAvg,'Alpha',0.05);

%prctile(reshape(squeeze(res.optCon(:,:,2)),[1,270]),50)