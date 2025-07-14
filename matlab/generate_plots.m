clear;

% --- FUNCTION DEFINITIONS ---

function result = percentageCompare(oldArray, newArray)
    % Calculates the percentage change between two arrays.
    oldArray = oldArray(:);
    newArray = newArray(:);
    result = (newArray - oldArray) ./ oldArray * 100;
end

function setBoxplot(data, side, limit, plot_type)
    % Configures and displays a boxplot with dynamically placed median text.
    data_for_median = data(:, side);
    m = median(data_for_median, 'omitnan');
    
    h = boxplot(data, 'Widths', 0.7); 
    ylim(limit);
    
    median_handle = h(6, side);
    if ishandle(median_handle)
        set(median_handle, 'Color', 'r', 'LineWidth', 2);
    end
    
    hold on;
    
    box_handle = h(5, side);
    if ~ishandle(box_handle)
        hold off;
        return;
    end
    
    box_ydata = get(box_handle, 'YData');
    q1 = min(box_ydata);
    q3 = max(box_ydata);
    
    box_xdata = get(box_handle, 'XData');
    box_half_width = (max(box_xdata) - min(box_xdata)) / 2;

    y_range = diff(get(gca, 'YLim'));
    vertical_space_needed = 0.1 * y_range;
    vertical_offset = 0.01 * y_range;
    horizontal_offset = 0.02;
    
    space_above = q3 - m;
    space_below = m - q1;
    
    place_laterally = true;
    
    if space_above > space_below && space_above > vertical_space_needed
        place_laterally = false;
        vertical_position = m + vertical_offset;
        vertical_alignment = 'bottom';
        horizontal_alignment = 'center';
        text_x_position = side;
    elseif space_below >= space_above && space_below > vertical_space_needed
        place_laterally = false;
        vertical_position = m - vertical_offset;
        vertical_alignment = 'top';
        horizontal_alignment = 'center';
        text_x_position = side;
    end
    
    if place_laterally
        if strcmp(plot_type, 'latency')
            vertical_position = m;
            vertical_alignment = 'middle';
        else
            vertical_position = m - vertical_offset;
            vertical_alignment = 'top';
        end
        
        if side == 1
            horizontal_alignment = 'left';
            text_x_position = side + box_half_width + horizontal_offset;
        else
            horizontal_alignment = 'right';
            text_x_position = side - box_half_width - horizontal_offset;
        end
    end
    
    text(text_x_position, vertical_position, sprintf('%.2f%%', m), ...
         'Color', 'r', 'FontSize', 16, ...
         'HorizontalAlignment', horizontal_alignment, ...
         'VerticalAlignment', vertical_alignment);
    
    hold off;
end

function h_ax = createExperimentSubplot(position, data_left, data_right, ylim_left, ylim_right, plot_type, ylabel_text, title_text)
    % Creates, configures, and styles a single subplot with two y-axes.
    h_ax = subplot('Position', position);

    % Left Y-Axis
    yyaxis left;
    setBoxplot([data_left, nan(size(data_left))], 1, ylim_left, plot_type);
    if ~isempty(ylabel_text)
        ylabel(ylabel_text, 'Interpreter', 'latex', 'Color', 'k');
    end

    % Right Y-Axis
    yyaxis right;
    setBoxplot([nan(size(data_right)), data_right], 2, ylim_right, plot_type);

    % Align Grid Lines
    num_ticks = 8;
    if strcmp(plot_type, 'billable')
        num_ticks = 9;
    end
    left_ticks = linspace(ylim_left(1), ylim_left(2), num_ticks);
    right_ticks = ylim_right(1) + (left_ticks - ylim_left(1)) .* (diff(ylim_right) / diff(ylim_left));
    h_ax.YAxis(1).TickValues = left_ticks;
    h_ax.YAxis(2).TickValues = right_ticks;

    % Common Axes Properties
    set(gca, 'xtick', [1 2], 'xticklabel', {'$\frac{WL - GCR}{GCR}$', '$\frac{WL - NoConc}{NoConc}$'}, 'TickLabelInterpreter', 'latex');
    box on;
    grid on;
    ax_current = gca;
    ax_current.GridColor = [0 0 0];
    ax_current.GridAlpha = 0.3;
    ax_current.FontSize = 16;
    ax_current.XAxis.FontSize = 24;

    if ~isempty(title_text)
        t = title(title_text, 'Interpreter', 'latex');
        t.Position(2) = t.Position(2) + 2;
    end
end

function setAndStyleTripleBoxplot(data1, data2, data3, limit, plot_type)
    % Combines data and creates a single boxplot with 3 groups, then styles each.
    full_data = [data1, data2, data3];
    h = boxplot(full_data, 'Widths', 0.7);
    if ~isempty(limit)
        ylim(limit);
    end

    hold on;

    for side = 1:3
        m = median(full_data(:, side), 'omitnan');
        median_handle = h(6, side);
        if ishandle(median_handle)
            set(median_handle, 'Color', 'r', 'LineWidth', 2);
        end

        box_handle = h(5, side);
        if ~ishandle(box_handle)
            continue;
        end

        box_ydata = get(box_handle, 'YData');
        q1 = min(box_ydata);
        q3 = max(box_ydata);
        box_xdata = get(box_handle, 'XData');
        box_half_width = (max(box_xdata) - min(box_xdata)) / 2;

        y_range = diff(get(gca, 'YLim'));
        vertical_space_needed = 0.1 * y_range;
        vertical_offset = 0.01 * y_range;
        horizontal_offset = 0.02;
        
        space_above = q3 - m;
        space_below = m - q1;
        
        place_laterally = true;
        
        if space_above > space_below && space_above > vertical_space_needed
            place_laterally = false;
            vertical_position = m + vertical_offset;
            vertical_alignment = 'bottom';
            horizontal_alignment = 'center';
            text_x_position = side;
        elseif space_below >= space_above && space_below > vertical_space_needed
            place_laterally = false;
            vertical_position = m - vertical_offset;
            vertical_alignment = 'top';
            horizontal_alignment = 'center';
            text_x_position = side;
        end
        
        if place_laterally
            if strcmp(plot_type, 'latency')
                vertical_position = m;
                vertical_alignment = 'middle';
            else
                vertical_position = m - vertical_offset;
                vertical_alignment = 'top';
            end
            
            if side == 3 % Rightmost boxplot
                horizontal_alignment = 'right';
                text_x_position = side - box_half_width - horizontal_offset;
            else % Left and middle boxplots
                horizontal_alignment = 'left';
                text_x_position = side + box_half_width + horizontal_offset;
            end
        end
        
        text(text_x_position, vertical_position, sprintf('%.2f%%', m), ...
             'Color', 'r', 'FontSize', 16, ...
             'HorizontalAlignment', horizontal_alignment, ...
             'VerticalAlignment', vertical_alignment);
    end
    hold off;
end

function h_ax = createWasteLessLatencySubplot(position, data1, data2, data3, ylabel_text)
    % Creates a single subplot with three boxplots for the WasteLess comparison.
    h_ax = subplot('Position', position);

    setAndStyleTripleBoxplot(data1, data2, data3, [], 'latency');
    
    ylabel(ylabel_text, 'Interpreter', 'latex', 'Color', 'k');

    % Set axes properties for the triple-boxplot plot
    labels = {'$\frac{WL - GCR}{GCR}$', '$\frac{WL - NoConc}{NoConc}$', '$\frac{WL - ProPack}{ProPack}$'};
    set(gca, 'xtick', [1 2 3], 'xticklabel', labels, 'TickLabelInterpreter', 'latex');
    box on;
    grid on;
    ax_current = gca;
    ax_current.GridColor = [0 0 0];
    ax_current.GridAlpha = 0.3;
    ax_current.FontSize = 16;
    ax_current.XAxis.FontSize = 24;
end

% --- MAIN SCRIPT ---

%--- Plot 1: 2x3 Grid Comparison ---
% Configuration
experiments_array = ["synch", "async", "paral"];
latency_ylim_left = [-85 -50];
latency_ylim_right = [-55 15];
billable_ylim_left = [100 1700];
billable_ylim_right = [-73 15];

% Figure and Layout Setup
figure_width_pixels = 1600;
figure_height_pixels = figure_width_pixels * (9 / 16);
h_fig1 = figure('units','pixels', 'Position',[100, 100, figure_width_pixels, figure_height_pixels]);

num_rows = 2;
num_cols = 3;
left_margin = 0.05;
right_margin = 0.02;
bottom_margin = 0.08;
top_margin = 0.08;
h_spacing = 0.05;
v_spacing = 0.07;

effective_width = 1 - left_margin - right_margin;
effective_height = 1 - bottom_margin - top_margin;
subplot_width = (effective_width - (num_cols - 1) * h_spacing) / num_cols;
subplot_height = (effective_height - (num_rows - 1) * v_spacing) / num_rows;
top_row_bottom = bottom_margin + subplot_height + v_spacing; 
bottom_row_bottom = bottom_margin; 

% Main Loop to Generate 2x3 Plots
for i = 1:length(experiments_array)
    experiment = experiments_array(i);
    res = load(fullfile("data", experiment + "_exp_data_processed.mat")); 

    gcrRtImp = percentageCompare(res.gcrRtAvg, res.wlRtAvg);
    ncRtImp = percentageCompare(res.ncRtAvg, res.wlRtAvg);
    gcrBillImp = percentageCompare(res.gcrBillAvg, res.wlBillAvg);
    ncBillImp = percentageCompare(res.ncBillAvg, res.wlBillAvg); 

    current_col_left = left_margin + (i - 1) * (subplot_width + h_spacing);

    title_text = sprintf('\\textbf{%s}', mlreportgen.utils.capitalizeFirstChar(experiment));
    latency_ylabel = '';
    billable_ylabel = '';
    if i == 1
        latency_ylabel = "\textbf{\% Latency}";
        billable_ylabel = "\textbf{\% Billable Instances}";
    end

    latency_pos = [current_col_left, top_row_bottom, subplot_width, subplot_height];
    createExperimentSubplot(latency_pos, gcrRtImp, ncRtImp, latency_ylim_left, latency_ylim_right, 'latency', latency_ylabel, title_text);

    billable_pos = [current_col_left, bottom_row_bottom, subplot_width, subplot_height];
    createExperimentSubplot(billable_pos, gcrBillImp, ncBillImp, billable_ylim_left, billable_ylim_right, 'billable', billable_ylabel, '');
end

set(h_fig1, 'DefaultAxesLooseInset', [0, 0, 0, 0]);
if ~exist('figures', 'dir')
   mkdir('figures')
end
exportgraphics(h_fig1, "figures/combined_performance_plots.pdf", 'ContentType', 'vector');
close(h_fig1);


%Run Kolmogorov-Smirnoff test


[gcrH, gcrP] = kstest2(res.wlRtAvg, res.gcrRtAvg, 'Alpha', 0.05);
[ncH, ncP] = kstest2(res.wlRtAvg, res.ncRtAvg, 'Alpha', 0.05);

% One-sided test to prove wlRtAvg is stochastically smaller (faster)
% H1: CDF of wlRtAvg is 'larger' than CDF of ncRtAvg
[h, p_value] = kstest2(res.wlRtAvg, res.ncRtAvg, 'Tail', 'larger', 'Alpha', 0.05);

%prctile(reshape(squeeze(res.optCon(:,:,2)),[1,270]),50)



% --- Plot 2: Single WasteLess Comparison ---
h_fig2 = figure('units','pixels', 'Position',[100, 100, 800, 600]);

res_wasteless = load(fullfile("data", "wless.mat"));
gcrRtImp1 = percentageCompare(res_wasteless.defconcRtAvg, res_wasteless.wlessRtAvg);
ncRtImp1 = percentageCompare(res_wasteless.noconcRtAvg, res_wasteless.wlessRtAvg);
ppRtImp1 = percentageCompare(res_wasteless.propackRtAvg, res_wasteless.wlessRtAvg);



% Use generous margins for a single plot
subplot_pos = [0.1, 0.1, 0.85, 0.8];
createWasteLessLatencySubplot(subplot_pos, gcrRtImp1, ncRtImp1, ppRtImp1, "\textbf{\% Latency}");

if ~exist('figures', 'dir')
   mkdir('figures')
end
exportgraphics(h_fig2, "figures/wasteless_latency_comparison_plot.pdf", 'ContentType', 'vector');
close(h_fig2);


h_fig3 = figure('units','pixels', 'Position',[100, 100, 800, 600]);
gcrBillImp1 = percentageCompare(res_wasteless.defbill, res_wasteless.wlessbill);
ncBillImp1 = percentageCompare(res_wasteless.noconcbill, res_wasteless.wlessbill);
ppBillImp1 = percentageCompare(res_wasteless.propackbill, res_wasteless.wlessbill);

createWasteLessLatencySubplot(subplot_pos, gcrBillImp1, ncBillImp1, ppBillImp1, "\textbf{\% Billable Instances}");
exportgraphics(h_fig3, "figures/wasteless_billable_comparison_plot.pdf", 'ContentType', 'vector');
close(h_fig3);

%Run Kolmogorov-Smirnoff test
[hdef,pdef]=kstest2(res_wasteless.wlessRtAvg,res_wasteless.defconcRtAvg,'Alpha',0.05);
[hno,pno]=kstest2(res_wasteless.wlessRtAvg,res_wasteless.noconcRtAvg,'Alpha',0.05);
[h, p_value2] = kstest2(res_wasteless.wlessRtAvg,res_wasteless.noconcRtAvg, 'Tail', 'larger', 'Alpha', 0.05);

[hpp,ppp]=kstest2(res_wasteless.wlessRtAvg,res_wasteless.propackRtAvg,'Alpha',0.05);

%prctile(reshape(squeeze(res.optCon(:,:,2)),[1,270]),50)