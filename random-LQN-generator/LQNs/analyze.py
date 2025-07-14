import os
import csv
import re
import argparse

def analyze_file(file_path):
    """
    Analyzes a single text file to extract specified metrics.

    Args:
        file_path (str): The full path to the file to be analyzed.

    Returns:
        A dictionary containing the calculated metrics, or None if the
        file content cannot be read.
    """
    synch_call_count = 0
    async_call_count = 0
    probabilistic_choice_count = 0
    ampersand_line_count = 0
    found_first_y = False  # Flag to track if the first 'y' has been found

    try:
        with open(file_path, 'r', encoding='utf-8', errors='ignore') as f:
            for line in f:
                # Strip leading/trailing whitespace to accurately check the first character
                stripped_line = line.strip()
                if not stripped_line:
                    continue

                # Count synchronous ('y') and asynchronous ('z') calls
                if stripped_line.startswith('y'):
                    if not found_first_y:
                        found_first_y = True  # Mark the first 'y' as found and ignore it for the count.
                    else:
                        synch_call_count += 1  # Count all subsequent 'y's.
                elif stripped_line.startswith('z'):
                    async_call_count += 1

                # Count lines with probabilistic ('+') or parallel ('&') choices
                if '+' in line:
                    probabilistic_choice_count += 1
                if '&' in line:
                    ampersand_line_count += 1
    except IOError as e:
        print(f"Error reading file {file_path}: {e}")
        return None

    # --- Calculations ---

    # Calculate call metrics
    total_calls = synch_call_count + async_call_count
    perc_async = round((async_call_count / total_calls) * 100, 2) if total_calls > 0 else 0

    # Calculate choice metrics
    parallel_choices_val = ampersand_line_count / 2
    if parallel_choices_val.is_integer():
        parallel_choices_val = int(parallel_choices_val)
    
    total_choices = probabilistic_choice_count + parallel_choices_val
    perc_paral = round((parallel_choices_val / total_choices) * 100, 2) if total_choices > 0 else 0

    return {
        'synch_calls': synch_call_count,
        'async_calls': async_call_count,
        'calls': total_calls,
        'perc_async': perc_async,
        'prob_choices': probabilistic_choice_count,
        'paral_choices': parallel_choices_val,
        'perc_paral': perc_paral,
    }

def print_table(headers, data_rows, title, summary_row=None):
    """
    Prints a list of dictionaries in a tabular format.
    Optionally includes a final summary row.

    Args:
        headers (list): A list of strings for the table headers.
        data_rows (list): A list of dictionaries, where each dictionary is a row.
        title (str): The title to print above the table.
        summary_row (dict, optional): A pre-calculated summary row to print.
    """
    if not data_rows and not summary_row:
        print(f"No data to display for {title}.")
        return

    # --- Prepare for printing ---
    # Calculate the maximum width for each column, including the summary row if it exists
    rows_for_width_calc = data_rows
    if summary_row:
        rows_for_width_calc = data_rows + [summary_row]
    
    column_widths = {key: len(key) for key in headers}
    for row in rows_for_width_calc:
        for key, value in row.items():
            column_widths[key] = max(column_widths.get(key, 0), len(str(value)))
    
    # --- Print Table ---
    header_line = " | ".join(header.ljust(column_widths[header]) for header in headers)
    separator_line = "-+-".join("-" * column_widths[header] for header in headers)

    print(f"\n--- {title} ---")
    print(header_line)
    print(separator_line)

    # Print data rows
    for row in data_rows:
        row_line = " | ".join(str(row.get(header, "")).ljust(column_widths[header]) for header in headers)
        print(row_line)

    # Print separator and summary row if it exists
    if summary_row:
        print(separator_line)
        summary_line = " | ".join(str(summary_row.get(header, "")).ljust(column_widths[header]) for header in headers)
        print(summary_line)
        
    print("-------------------\n")

def print_latex_table(headers, data_rows):
    """
    Generates and prints a LaTeX table from the summary data.

    Args:
        headers (list): A list of strings for the table headers.
        data_rows (list): A list of summary dictionaries.
    """
    if not data_rows:
        return

    # Exclude 'calls' column specifically for the LaTeX output
    latex_display_headers = [h for h in headers if h != 'calls']

    # Create LaTeX-friendly headers
    latex_headers_map = {
        'folder': 'Folder',
        'functions': 'Functions',
        'calls': 'Total Calls',
        'synch_calls': 'Synch Calls',
        'async_calls': 'Async Calls',
        'perc_async': '\% Async',
        'prob_choices': 'Prob Choices',
        'paral_choices': 'Paral Choices',
        'perc_paral': '\% Paral'
    }
    
    # Define column alignment: left for the first, center for the rest
    num_columns = len(latex_display_headers)
    col_format = 'l' + 'c' * (num_columns - 1)

    print("\n--- LaTeX Summary Table ---")
    print("% For this table to compile, you need the 'booktabs' package: \\usepackage{booktabs}")
    print("\\begin{table}[h!]")
    print("\\centering")
    print("\\caption{Summary of Folder Analysis}")
    print(f"\\begin{{tabular}}{{{col_format}}}")
    print("\\toprule")

    # Print header row
    header_row = " & ".join([latex_headers_map.get(h, h) for h in latex_display_headers])
    print(header_row + " \\\\")
    print("\\midrule")

    # Print data rows
    for row in data_rows:
        row_values = []
        for header in latex_display_headers:
            value = str(row.get(header, ''))
            # Escape special LaTeX characters
            value = value.replace('_', '\\_').replace('±', '$\\pm$')
            row_values.append(value)
        print(" & ".join(row_values) + " \\\\")

    print("\\bottomrule")
    print("\\end{tabular}")
    print("\\label{tab:folder_summary}")
    print("\\end{table}")
    print("---------------------------\n")


def process_folder(input_folder, output_csv_path):
    """
    Processes all .lqn files in a given folder, sorts them by filename,
    writes the analysis to a CSV file, and returns the summary statistics.

    Args:
        input_folder (str): The path to the folder containing .lqn files.
        output_csv_path (str): The path to write the output CSV file.
        
    Returns:
        A dictionary containing the summary statistics for the folder.
    """
    filename_pattern = re.compile(r'lqn\d+-(\d+)f\.lqn')
    if not os.path.isdir(input_folder):
        print(f"Error: Input folder '{input_folder}' not found.")
        return None

    all_results = []
    print(f"Scanning files in '{input_folder}'...")

    for filename in os.listdir(input_folder):
        match = filename_pattern.match(filename)
        if match:
            x_value = int(match.group(1))
            functions = x_value - 1
            full_path = os.path.join(input_folder, filename)
            analysis_results = analyze_file(full_path)
            if analysis_results:
                all_results.append({'filename': filename, 'functions': functions, **analysis_results})
        else:
            continue

    all_results.sort(key=lambda item: item['filename'])

    fieldnames = ['filename', 'functions', 'calls', 'synch_calls', 'async_calls', 'perc_async', 'prob_choices', 'paral_choices', 'perc_paral']

    # --- Calculate Summary Statistics ---
    summary_stats = {}
    summary_row_for_table = None
    if all_results:
        num_rows = len(all_results)
        for header in fieldnames:
            if header == 'filename': continue
            try:
                column_data = [row.get(header, 0) for row in all_results]
                mean = sum(column_data) / num_rows
                variance = sum([(x - mean) ** 2 for x in column_data]) / num_rows
                std_dev = variance ** 0.5
                summary_stats[header] = f"{mean:.2f} ± {std_dev:.2f}"
            except (TypeError, ZeroDivisionError):
                summary_stats[header] = 'N/A'
        summary_row_for_table = {'filename': 'Avg ± Std Dev', **summary_stats}

    # --- Write individual CSV and print table ---
    try:
        with open(output_csv_path, 'w', newline='', encoding='utf-8') as csvfile:
            writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(all_results)
        print(f"\nProcessing complete for '{input_folder}'. Results saved to '{output_csv_path}'")
        table_title = f"Analysis for {os.path.basename(input_folder)}"
        print_table(fieldnames, all_results, table_title, summary_row=summary_row_for_table)
    except IOError as e:
        print(f"Error writing to output file {output_csv_path}: {e}")
        return None
    except Exception as e:
        print(f"An unexpected error occurred: {e}")
        return None
        
    return summary_stats

def write_summary_csv(summary_data, filename):
    """
    Writes the collected summary data to a final CSV file.
    
    Args:
        summary_data (list): A list of summary dictionaries, one for each folder.
        filename (str): The name of the output summary CSV file.
    """
    if not summary_data:
        print("No summary data to write.")
        return
        
    # Define the headers for the summary file.
    fieldnames = ['folder', 'functions', 'calls', 'synch_calls', 'async_calls', 'perc_async', 'prob_choices', 'paral_choices', 'perc_paral']
    
    try:
        with open(filename, 'w', newline='', encoding='utf-8') as csvfile:
            writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(summary_data)
        print(f"Overall summary saved to '{filename}'")
    except IOError as e:
        print(f"Error writing summary file {filename}: {e}")

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="Analyze .lqn files in a directory and output a CSV summary.", formatter_class=argparse.RawTextHelpFormatter)
    parser.add_argument('-i', '--input', help='Path to a specific input folder. If not provided, all subdirectories in the current folder will be processed.')
    args = parser.parse_args()

    folders_to_process = []
    if args.input:
        folders_to_process.append(os.path.normpath(args.input))
    else:
        print("No input folder specified. Scanning all subdirectories in the current location...")
        current_dir = os.getcwd()
        folders_to_process = [os.path.join(current_dir, d) for d in os.listdir(current_dir) if os.path.isdir(os.path.join(current_dir, d))]

    if not folders_to_process:
        print("No folders found to process.")
    else:
        all_folder_summaries = []
        for folder_path in folders_to_process:
            folder_name = os.path.basename(folder_path)
            output_csv_path = f"{folder_name}.csv"
            summary = process_folder(folder_path, output_csv_path)
            if summary:
                summary_with_name = {'folder': folder_name, **summary}
                all_folder_summaries.append(summary_with_name)
        
        if all_folder_summaries:
            summary_csv_filename = 'summary_of_all_folders.csv'
            write_summary_csv(all_folder_summaries, summary_csv_filename)
            
            summary_headers = ['folder', 'functions', 'calls', 'synch_calls', 'async_calls', 'perc_async', 'prob_choices', 'paral_choices', 'perc_paral']
            print_table(summary_headers, all_folder_summaries, title="Overall Summary of All Folders")
            
            # Print the final summary as a LaTeX table
            print_latex_table(summary_headers, all_folder_summaries)
