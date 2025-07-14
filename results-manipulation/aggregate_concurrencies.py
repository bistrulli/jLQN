import pandas as pd
import argparse
from pathlib import Path
from typing import List, Any

def aggregate_scaled_conc(input_folder: str) -> List[Any]:
    """
    Navigates a directory structure to find and process CSV files.

    This function searches for files matching the pattern:
    /input_folder/*/Entr0/experiments/validation_metrics.csv

    It reads the 'ScaledConc' column from each found CSV file and
    aggregates all the values into a single list.

    Args:
        input_folder: The path to the root folder to start the search from.

    Returns:
        A list containing all the values from the 'ScaledConc' column
        of all the files found.
    """
    # Define the starting path and the glob pattern to find the target files.
    # The '*' is a wildcard that matches any directory name at that level.
    root_path = Path(input_folder)
    glob_pattern = "*/Entr0/experiments/validation_metrics.csv"
    
    # Initialize an empty list to store the aggregated values.
    all_scaled_conc_values = []

    # Use glob to find all files matching the pattern
    csv_files = list(root_path.glob(glob_pattern))

    if not csv_files:
        # This is not an error, just a folder with no matching files.
        # The main loop will report which folder is being processed.
        return all_scaled_conc_values

    # Iterate over each file path found by glob
    for file_path in csv_files:
        try:
            # Read the CSV file into a pandas DataFrame
            df = pd.read_csv(file_path)

            # Check if the 'ScaledConc' column exists in the DataFrame
            if 'ScaledConc' in df.columns:
                # Extract the values from the 'ScaledConc' column
                scaled_conc_column = df['ScaledConc']
                
                # Extend the main list with the values from the current file.
                all_scaled_conc_values.extend(scaled_conc_column.dropna().astype(int).tolist())
            else:
                print(f"    - Warning: 'ScaledConc' column not found in '{file_path}'. Skipping.")

        except pd.errors.EmptyDataError:
            print(f"    - Warning: The file '{file_path}' is empty. Skipping.")
        except Exception as e:
            print(f"    - Error processing file '{file_path}': {e}")

    return all_scaled_conc_values

if __name__ == "__main__":
    # Set up the command-line argument parser
    parser = argparse.ArgumentParser(
        description="For each subfolder in a root folder, aggregate 'ScaledConc' values and calculate percentiles."
    )
    parser.add_argument(
        "root_folder", 
        type=str, 
        help="The path to the root folder containing the subfolders to process."
    )
    parser.add_argument(
        "-o", "--output-file",
        type=str,
        default="percentiles.csv",
        help="Path for the output CSV file (default: percentiles.csv)"
    )

    # Parse the arguments provided by the user
    args = parser.parse_args()
    root_path = Path(args.root_folder)

    # Validate that the provided path is a directory
    if not root_path.is_dir():
        print(f"Error: The provided path '{root_path}' is not a valid directory.")
        exit(1)

    print(f"Processing subdirectories in '{root_path}'...")
    
    # List to hold the results for each subfolder
    all_results = []

    # Iterate over each item in the root folder
    for sub_folder in sorted(root_path.iterdir()):
        # Process only if the item is a directory
        if sub_folder.is_dir():
            print(f"\n--- Processing folder: {sub_folder.name} ---")
            
            # Call the aggregation function for the subfolder
            resulting_list = aggregate_scaled_conc(str(sub_folder))

            # If data was found, calculate percentiles and store them
            if resulting_list:
                print(f"  - Found {len(resulting_list)} values. Calculating percentiles...")
                series = pd.Series(resulting_list)
                # Use 'nearest' interpolation to ensure results are integers from the data
                p50 = series.quantile(0.50, interpolation='nearest')
                p95 = series.quantile(0.95, interpolation='nearest')
                p99 = series.quantile(0.99, interpolation='nearest')

                all_results.append({
                    'folder_name': sub_folder.name,
                    '50th': p50,
                    '95th': p95,
                    '99th': p99
                })
            else:
                print(f"  - No data found in '{sub_folder.name}'.")
    
    # After processing all folders, create and save the final CSV
    if all_results:
        print("\n--- Aggregation Complete ---")
        results_df = pd.DataFrame(all_results)
        
        try:
            # Save the DataFrame to a CSV file
            results_df.to_csv(args.output_file, index=False)
            print(f"Results successfully saved to '{args.output_file}'")
            print("\nFinal Data:")
            print(results_df)
        except Exception as e:
            print(f"\nError saving CSV file to '{args.output_file}': {e}")
    else:
        print("\n--- No data was collected, so no output file was created. ---")
