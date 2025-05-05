import csv
from pathlib import Path
import sys
import os  # Imported to create the directory

# Try to import tabulate, if not available, inform the user
try:
    from tabulate import tabulate
    TABULATE_AVAILABLE = True
except ImportError:
    TABULATE_AVAILABLE = False
    print("INFO: 'tabulate' library not found. Install it with 'pip install tabulate' to see formatted output.")

# --- Configuration ---

# Define the name of the subfolder
EXPERIMENTS_DIR_NAME = "experiments"
# Create a Path object for the subfolder
experiments_dir = Path(EXPERIMENTS_DIR_NAME)

# Dictionary for _stats.csv files (RPS, RT) - Relative paths to experiments_dir
STATS_FILES = {
    "NC": experiments_dir / "NC_stats.csv",
    "GCR": experiments_dir / "GCR_stats.csv",
    "WL": experiments_dir / "WL_stats.csv",
}

# Dictionary for _metrics.csv files (Sum of BILL column) - Relative paths to experiments_dir
METRICS_FILES = {
    "NC": experiments_dir / "NC_metrics.csv",
    "GCR": experiments_dir / "GCR_metrics.csv",
    "WL": experiments_dir / "WL_metrics.csv",
}

# Path for the output CSV file - Inside experiments_dir
OUTPUT_FILE = experiments_dir / "summary_stats.csv"

# Target column names
COLUMN_RPS = "Requests/s"
COLUMN_RT = "Average Response Time"
COLUMN_BILL = "BILL"

# Header for the output CSV
OUTPUT_HEADERS = ["Algorithm", "RPS", "RT", "BILL"]

# Placeholder for missing values
MISSING_VALUE = "N/A"

# --- Function Definitions ---

def extract_last_row_data(filepath: Path, rps_col: str, rt_col: str) -> tuple | None:
    """Reads the last row of a stats CSV file and extracts the RPS and RT columns."""
    if not filepath.is_file():
        print(f"Error: Stats file '{filepath}' not found. Skipping.", file=sys.stderr)
        return None
    try:
        with filepath.open(mode='r', newline='', encoding='utf-8-sig') as infile:
            reader = csv.DictReader(infile)
            rows = list(reader)
            if not rows:
                print(f"Warning: Stats file '{filepath}' is empty or has only a header. Skipping.", file=sys.stderr)
                return None
            last_row = rows[-1]
            rps_value = last_row.get(rps_col, MISSING_VALUE)
            rt_value = last_row.get(rt_col, MISSING_VALUE)
            if rps_value == MISSING_VALUE:
                print(f"Warning: Column '{rps_col}' not found in the last row of '{filepath}'.", file=sys.stderr)
            if rt_value == MISSING_VALUE:
                print(f"Warning: Column '{rt_col}' not found in the last row of '{filepath}'.", file=sys.stderr)
            print(f"  Extracted RPS={rps_value}, RT={rt_value} from '{filepath}'")
            return (rps_value, rt_value)
    except Exception as e:
        print(f"Error: Unable to read/process stats file '{filepath}': {e}", file=sys.stderr)
        return None

def calculate_bill_sum(filepath: Path, bill_col: str) -> float | None:
    """Calculates the sum of numeric values in the specified column of a metrics CSV file."""
    if not filepath.is_file():
        print(f"Error: Metrics file '{filepath}' not found. Unable to calculate BILL.", file=sys.stderr)
        return None
    total_bill = 0.0
    rows_processed_for_bill = 0
    try:
        with filepath.open(mode='r', newline='', encoding='utf-8-sig') as infile:
            reader = csv.DictReader(infile)
            if bill_col not in reader.fieldnames:
                print(f"Error: Column '{bill_col}' not found in the header of '{filepath}'. Unable to calculate BILL.", file=sys.stderr)
                return None
            for i, row in enumerate(reader):
                try:
                    bill_value_str = row.get(bill_col)
                    if bill_value_str is not None and bill_value_str.strip() != '':
                        bill_value = float(bill_value_str)
                        total_bill += bill_value
                        rows_processed_for_bill += 1
                except ValueError:
                    print(f"Warning: Non-numeric value ('{bill_value_str}') found in column '{bill_col}', row {i+2} of '{filepath}'. Row ignored for BILL sum.", file=sys.stderr)
                except Exception as row_e:
                    print(f"Warning: Error processing row {i+2} for BILL in '{filepath}': {row_e}. Row ignored.", file=sys.stderr)
        if rows_processed_for_bill > 0:
            print(f"  Calculated BILL sum: {total_bill:.4f} from {rows_processed_for_bill} values in '{filepath}'")
            return total_bill
        else:
            print(f"Warning: No numeric values found/processed for column '{bill_col}' in '{filepath}'. BILL sum considered 0.0.", file=sys.stderr)
            return 0.0
    except Exception as e:
        print(f"Error: Unable to read/process metrics file '{filepath}' for BILL sum: {e}", file=sys.stderr)
        return None

# --- Data Extraction ---

summary_data_rows = []
print("Starting data extraction...")

# Iterate over the algorithms defined in the STATS_FILES dictionary
for algorithm, stats_filepath in STATS_FILES.items():  # stats_filepath now includes 'experiments/'
    print(f"\nProcessing algorithm: {algorithm}")
    print(f"  Stats File: {stats_filepath}")

    # 1. Extract RPS and RT from the _stats.csv file
    rps_rt_data = extract_last_row_data(stats_filepath, COLUMN_RPS, COLUMN_RT)
    rps = rps_rt_data[0] if rps_rt_data else MISSING_VALUE
    rt = rps_rt_data[1] if rps_rt_data else MISSING_VALUE

    # 2. Calculate BILL sum from the corresponding _metrics.csv file
    bill_sum = MISSING_VALUE
    metrics_filepath = METRICS_FILES.get(algorithm)  # metrics_filepath now includes 'experiments/'

    if metrics_filepath:
        print(f"  Metrics File: {metrics_filepath}")
        calculated_bill = calculate_bill_sum(metrics_filepath, COLUMN_BILL)
        if calculated_bill is not None:
            bill_sum = f"{calculated_bill:.4f}"
    else:
        print(f"Warning: Metrics file not defined for algorithm '{algorithm}'. BILL set to '{MISSING_VALUE}'.", file=sys.stderr)

    # 3. Add the complete row
    summary_data_rows.append([algorithm, rps, rt, bill_sum])

# --- Output Generation ---

if not summary_data_rows:
    print(f"\nError: No valid data collected. File '{OUTPUT_FILE}' not generated.", file=sys.stderr)
    sys.exit(1)

# --- Ensure the output directory exists ---
try:
    # Create the 'experiments' directory if it doesn't exist.
    # parents=True creates parent directories if necessary (not here, but good practice)
    # exist_ok=True does not raise an error if the directory already exists
    experiments_dir.mkdir(parents=True, exist_ok=True)
    print(f"Verified/created output directory: '{experiments_dir.resolve()}'")  # .resolve() shows the absolute path
except OSError as e:
    print(f"Error: Unable to create output directory '{experiments_dir}': {e}", file=sys.stderr)
    sys.exit(1)
# --- End directory check ---

print(f"\nWriting summary data to '{OUTPUT_FILE}'...")  # OUTPUT_FILE is now inside 'experiments'

try:
    # Open the output file (which now has the full path experiments/...)
    with OUTPUT_FILE.open(mode='w', newline='', encoding='utf-8') as outfile:
        writer = csv.writer(outfile)
        writer.writerow(OUTPUT_HEADERS)
        writer.writerows(summary_data_rows)
    print("Summary CSV file successfully created.")

    # --- Print with Tabulate ---
    print("\n--- Summary Table ---")
    if TABULATE_AVAILABLE:
        print(tabulate(summary_data_rows, headers=OUTPUT_HEADERS, tablefmt="psql"))
    else:
        print(",".join(OUTPUT_HEADERS))
        for row in summary_data_rows:
            print(",".join(map(str, row)))
    print("--- End Table ---")

except IOError as e:
    print(f"Error: Unable to write to output file '{OUTPUT_FILE}': {e}", file=sys.stderr)
    sys.exit(1)
except Exception as e:
    print(f"Unexpected error during file writing or table generation: {e}", file=sys.stderr)
    sys.exit(1)

print("\nProcessing completed.")