import argparse
import pandas as pd
from pathlib import Path
import matplotlib.pyplot as plt # Import matplotlib

def find_and_process_csvs(search_directory: str):
    """
    Recursively finds CSV files matching specific names (NC, WL, GCR_metrics.csv),
    groups them by their parent experiment directory, extracts specified metrics,
    and aggregates them into a new pandas DataFrame.
    """
    base_path = Path(search_directory)
    # Files to look for
    target_filenames_set = {"GCR_metrics.csv", "NC_metrics.csv", "WL_metrics.csv"}

    # --- Configuration for input CSV column names ---
    # PLEASE ADJUST THESE if your CSV columns are named differently
    COL_FOR_RPS = 'RPS'          # Expected column name for Requests Per Second
    COL_FOR_RT = 'RT' # Expected column name for Average Response Time
    COL_FOR_BILL = 'BILL'       # Expected column name for Billing Amount
    # --- End Configuration ---

    aggregated_data_list = []
    # To group CSVs: Key: parent directory path, Value: dict {csv_type: path_to_csv}
    experiment_groups = {}

    print(f"Searching for specific metrics CSV files in: {base_path.resolve()}")
    print(f"Looking for files named: {', '.join(sorted(list(target_filenames_set)))}\\n")
    print(f"Expecting the following columns in input CSVs:")
    print(f"  For RPS: '{COL_FOR_RPS}'")
    print(f"  For RT: '{COL_FOR_RT}'")
    print(f"  For BILL: '{COL_FOR_BILL}'")
    print("If these are incorrect, please modify the script constants COL_FOR_RPS, COL_FOR_RT, COL_FOR_BILL.")
    print("-" * 50)

    for csv_file_path in base_path.rglob('*.csv'):
        if csv_file_path.name in target_filenames_set:
            experiment_dir_path = csv_file_path.parent
            # Determine experiment type (NC, WL, GCR) from filename
            experiment_type = csv_file_path.name.split('_')[0].upper()

            if experiment_dir_path not in experiment_groups:
                experiment_groups[experiment_dir_path] = {}
            experiment_groups[experiment_dir_path][experiment_type] = csv_file_path

    if not experiment_groups:
        print("No experiment directories containing the target CSV files were found.")
        return

    for experiment_dir_path, csv_files_map in experiment_groups.items():
        # experiment_set_name = experiment_dir_path.name # Old: name of the CSV's parent directory
        # New: name of the directory two levels above the CSV's parent directory
        try:
            experiment_set_name = experiment_dir_path.parent.parent.name
        except AttributeError: # Handles cases where the path is not deep enough
            print(f"    Warning: Could not determine ExperimentSet name two levels above {experiment_dir_path}. Path too shallow.")
            print(f"             Using fallback name: {experiment_dir_path.name}")
            experiment_set_name = experiment_dir_path.name # Fallback to parent's name

        print(f"Processing Experiment Set: '{experiment_set_name}' (from directory: {experiment_dir_path.parent.parent if experiment_dir_path.parent else experiment_dir_path})")

        for exp_type in ["NC", "WL", "GCR"]: # Process in a defined order for consistent output
            if exp_type in csv_files_map:
                csv_path = csv_files_map[exp_type]
                print(f"  Reading {exp_type}_metrics.csv (Path: {csv_path})")
                try:
                    df_metric = pd.read_csv(csv_path)
                    if df_metric.empty:
                        print(f"    Warning: {csv_path.name} is empty. Skipping.")
                        rps_value, rt_value, bill_value = float('nan'), float('nan'), float('nan')
                    else:
                        rps_value = df_metric[COL_FOR_RPS].mean() if COL_FOR_RPS in df_metric.columns else float('nan')
                        rt_value = df_metric[COL_FOR_RT].mean() if COL_FOR_RT in df_metric.columns else float('nan')
                        bill_value = df_metric[COL_FOR_BILL].mean() if COL_FOR_BILL in df_metric.columns else float('nan')
                        
                        if COL_FOR_RPS not in df_metric.columns:
                             print(f"    Warning: Column '{COL_FOR_RPS}' for RPS not found in {csv_path.name}.")
                        if COL_FOR_RT not in df_metric.columns:
                             print(f"    Warning: Column '{COL_FOR_RT}' for RT not found in {csv_path.name}.")
                        if COL_FOR_BILL not in df_metric.columns:
                             print(f"    Warning: Column '{COL_FOR_BILL}' for BILL not found in {csv_path.name}.")

                    aggregated_data_list.append({
                        "ExperimentSet": experiment_set_name,
                        "NAME": exp_type,
                        "RPS": rps_value,
                        "RT": rt_value,
                        "BILL": bill_value,
                    })
                    print(f"    Extracted for {exp_type}: RPS={rps_value:.2f}, RT={rt_value:.2f}, BILL={bill_value:.2f}")

                except pd.errors.EmptyDataError:
                    print(f"    Warning: {csv_path.name} is empty. Skipping (already handled).")
                except KeyError as e:
                    print(f"    Critical Warning: Column {e} (expected for metrics) not found in {csv_path.name}. NaN will be used.")
                    # This case should ideally be caught by the specific checks above.
                    # If it reaches here, it might be an unexpected column that pandas tries to access.
                    aggregated_data_list.append({
                        "ExperimentSet": experiment_set_name,
                        "NAME": exp_type, "RPS": float('nan'), "RT": float('nan'), "BILL": float('nan')
                    })
                except Exception as e:
                    print(f"    Error reading or processing {csv_path.name}: {e}")
                    aggregated_data_list.append({
                        "ExperimentSet": experiment_set_name,
                        "NAME": exp_type, "RPS": float('nan'), "RT": float('nan'), "BILL": float('nan')
                    })
            else:
                print(f"  Warning: {exp_type}_metrics.csv not found in {experiment_dir_path}. Appending row with NaNs.")
                aggregated_data_list.append({
                    "ExperimentSet": experiment_set_name,
                    "NAME": exp_type, "RPS": float('nan'), "RT": float('nan'), "BILL": float('nan')
                })
        print("-" * 50)

    if not aggregated_data_list:
        print("No data was aggregated. Check warnings above.")
        return

    final_df = pd.DataFrame(aggregated_data_list)
    
    # Define new percentage column names to reflect percentage CHANGE
    rps_perc_change_col = 'RPS(%)'
    rt_perc_change_col = 'RT(%)'
    bill_perc_change_col = 'BILL(%)'

    # Initialize new columns with NaN or pd.NA for pandas > 1.0
    final_df[rps_perc_change_col] = pd.NA
    final_df[rt_perc_change_col] = pd.NA
    final_df[bill_perc_change_col] = pd.NA

    # Iterate over each ExperimentSet to calculate percentages relative to NC
    for exp_set_name in final_df['ExperimentSet'].unique():
        set_group = final_df[final_df['ExperimentSet'] == exp_set_name]
        nc_row = set_group[set_group['NAME'] == 'NC']

        if not nc_row.empty:
            rps_nc_val = nc_row['RPS'].iloc[0]
            rt_nc_val = nc_row['RT'].iloc[0]
            bill_nc_val = nc_row['BILL'].iloc[0]

            for index, current_row_in_set in set_group.iterrows():
                # RPS Percentage Change
                current_rps = current_row_in_set['RPS']
                if pd.notna(current_rps) and pd.notna(rps_nc_val):
                    if rps_nc_val != 0:
                        final_df.loc[index, rps_perc_change_col] = ((current_rps - rps_nc_val) / rps_nc_val) * 100
                    elif current_rps == rps_nc_val: # Both are 0, so 0% change
                        final_df.loc[index, rps_perc_change_col] = 0.0
                    # Else (rps_nc_val is 0, current_rps is not 0) -> pandas handles as inf/-inf
                
                # RT Percentage Change
                current_rt = current_row_in_set['RT']
                if pd.notna(current_rt) and pd.notna(rt_nc_val):
                    if rt_nc_val != 0:
                        final_df.loc[index, rt_perc_change_col] = ((current_rt - rt_nc_val) / rt_nc_val) * 100
                    elif current_rt == rt_nc_val: # Both are 0
                        final_df.loc[index, rt_perc_change_col] = 0.0

                # BILL Percentage Change
                current_bill = current_row_in_set['BILL']
                if pd.notna(current_bill) and pd.notna(bill_nc_val):
                    if bill_nc_val != 0:
                        final_df.loc[index, bill_perc_change_col] = ((current_bill - bill_nc_val) / bill_nc_val) * 100
                    elif current_bill == bill_nc_val: # Both are 0
                        final_df.loc[index, bill_perc_change_col] = 0.0
        else:
            print(f"    Info: NC data not found for ExperimentSet '{exp_set_name}'. Percentage change columns will remain NA for this set.")
    
    # Ensure correct column order, including new percentage change columns
    output_columns = ["ExperimentSet", "NAME", "RPS", "RT", "BILL", rps_perc_change_col, rt_perc_change_col, bill_perc_change_col]
    # Filter and reorder columns based on output_columns, handling missing ones
    final_df = final_df.reindex(columns=output_columns)

    print("\\nFinal Aggregated DataFrame:")
    print(final_df.to_string())

    # Example: Save to a new CSV file in the original search directory
    # output_csv_filename = "aggregated_metrics_summary.csv"
    # output_csv_path = base_path / output_csv_filename
    # try:
    #     final_df.to_csv(output_csv_path, index=False, float_format='%.2f')
    #     print(f"\\nAggregated data also saved to: {output_csv_path.resolve()}")
    # except Exception as e:
    #     print(f"\\nError saving aggregated data to CSV: {e}")

    return final_df


def create_boxplots(df: pd.DataFrame):
    """
    Creates boxplots for RPS(%), RT(%), and BILL(%) metrics, 
    grouped by WL and GCR experiment types.
    """
    print("\nGenerating boxplots...")
    metric_columns = ['RPS(%)', 'RT(%)', 'BILL(%)']
    experiment_types_to_plot = ['WL', 'GCR']

    for metric_col in metric_columns:
        plt.figure(figsize=(8, 6))
        data_to_plot = []
        labels = []

        # Prepare data for WL and GCR for the current metric
        wl_data = df[(df['NAME'] == 'WL') & pd.notna(df[metric_col])][metric_col].tolist()
        gcr_data = df[(df['NAME'] == 'GCR') & pd.notna(df[metric_col])][metric_col].tolist()
        
        plot_title = f'Distribution of {metric_col} (vs NC)'
        y_label = f'{metric_col} (Percentage Change from NC)'

        if not wl_data and not gcr_data:
            print(f"  Skipping plot for {metric_col}: No data available for WL or GCR after filtering NaNs.")
            plt.close() # Close the empty figure
            continue

        current_plot_data = []
        current_labels = []

        if wl_data:
            current_plot_data.append(wl_data)
            current_labels.append('WL')
        else:
            print(f"  Info: No valid data for WL in {metric_col} plot.")

        if gcr_data:
            current_plot_data.append(gcr_data)
            current_labels.append('GCR')
        else:
            print(f"  Info: No valid data for GCR in {metric_col} plot.")

        if not current_plot_data: # Should be caught by the earlier check, but as a safeguard
            print(f"  Skipping plot for {metric_col}: No data to plot after individual WL/GCR checks.")
            plt.close()
            continue

        plt.boxplot(current_plot_data, labels=current_labels, patch_artist=True, medianprops=dict(color="black"))
        
        plt.title(plot_title)
        plt.ylabel(y_label)
        plt.xlabel("Experiment Type")
        plt.grid(True, linestyle='--', alpha=0.7)
        plt.tight_layout() # Adjust layout to prevent overlapping titles/labels
        plt.show()
        print(f"  Displayed plot for {metric_col}.")

    print("Boxplot generation complete.")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Find and process specific metrics CSV files (GCR_metrics.csv, NC_metrics.csv, WL_metrics.csv) in a directory, aggregate the results, and generate boxplots."
    )
    parser.add_argument(
        "search_directory", 
        type=str, 
        help="The directory to search recursively for CSV files."
    )
    
    args = parser.parse_args()
    
    if not Path(args.search_directory).is_dir():
        print(f"Error: The directory '{args.search_directory}' does not exist or is not a directory.")
    else:
        final_df = find_and_process_csvs(args.search_directory)
        if final_df is not None and not final_df.empty:
            create_boxplots(final_df) # Call the new plotting function
        else:
            print("Skipping boxplot generation as no data was aggregated or DataFrame is empty.") 