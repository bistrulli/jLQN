import re
import csv
import argparse

def extract_and_save_data(input_txt_file, output_csv_file):
    """
    Extracts Response Time (RT) and Billable Instance (BILL) data
    for each algorithm (NC, GCR, WL) from a text file, sorts them,
    and saves them to a CSV file.

    Args:
        input_txt_file (str): The path to the input text file.
        output_csv_file (str): The path to the output CSV file.
    """
    data_for_csv = []
    current_experiment = None

    with open(input_txt_file, 'r') as f:
        content = f.read()

    # Split the content into blocks for each experiment
    # Each block starts with "==================================================\nRunning comparison in: /home/rpizziol/jLQN/output/20250523/" 
    experiment_blocks = re.split(r'==================================================\nRunning comparison in: /home/robb/git/jLQN/output/experiments_paper/', content)

    # The first element of experiment_blocks will be empty or just the initial header, so we skip it
    for block in experiment_blocks[1:]:
        # Extract the experiment name (e.g., lqn13-6f.lqn) 
        match_exp_name = re.search(r'(lqn\d{2}-\d{1,2}f\.lqn)/Entr0', block)
        if match_exp_name:
            current_experiment = match_exp_name.group(1)

        # Search for the "Summary Table" section 
        summary_table_match = re.search(r'---\s*Summary Table\s*---\n\s*\+.*?\n((?:\|.*?\n)+)\+-------------', block, re.DOTALL)
        if summary_table_match:
            summary_table_content = summary_table_match.group(1)
            lines = summary_table_content.strip().split('\n')

            # Skip header rows if present and the separator row 
            # The first useful row is after the separator |-------------+----------... 
            data_lines = [line for line in lines if not line.startswith('|-------------')]

            for line in data_lines:
                # Extract values using regex
                # Group 1: Algorithm 
                # Group 2: RPS (ignored for this purpose) 
                # Group 3: RT 
                # Group 4: BILL 
                match = re.search(r'\|\s*([a-zA-Z]+)\s*\|\s*([\d\.-]+)\s*\|\s*([\d\.-]+)\s*\|\s*([\d\.e\+\-]+)\s*\|', line)
                if match:
                    algorithm = match.group(1).strip()
                    rt = float(match.group(3))
                    bill = float(match.group(4).replace('e+', 'e')) # Handles scientific notation format

                    data_for_csv.append({
                        'Experiment': current_experiment,
                        'Algorithm': algorithm,
                        'RT': rt,
                        'BILL': bill
                    })

    # --- NEW PART: SORTING THE DATA ---
    def sort_key(item):
        # Extract the number from the experiment (e.g., from 'lqn13-6f.lqn' to 13)
        exp_num_match = re.search(r'lqn(\d+)', item['Experiment'])
        exp_number = int(exp_num_match.group(1)) if exp_num_match else 0
        
        # Return a tuple to sort first by experiment number, then by algorithm name
        return (exp_number, item['Algorithm'])

    data_for_csv.sort(key=sort_key)
    # --- END NEW PART ---

    # Write data to the CSV file
    with open(output_csv_file, 'w', newline='') as csvfile:
        fieldnames = ['Experiment', 'Algorithm', 'RT', 'BILL']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

        writer.writeheader()
        for row in data_for_csv:
            writer.writerow(row)

    print(f"Data extracted and saved to '{output_csv_file}'")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Extracts Response Time and Billable Instance data from a text file, sorts it, and saves it to a CSV.")

    parser.add_argument(
        '-i', '--input_file',
        type=str,
        default='allres.txt',
        help='The path to the input text file (default: allres.txt)'
    )

    parser.add_argument(
        '-o', '--output_file',
        type=str,
        default='experiment_data.csv',
        help='The path to the output CSV file (default: experiment_data.csv)'
    )

    args = parser.parse_args()

    extract_and_save_data(args.input_file, args.output_file)
