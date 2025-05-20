import csv
import os
import pandas as pd
import argparse

def estimateServiceTime(csv_file_path):
    """
    Estimates the service time of entries using the utilization law: S = U / X
    where S is service time, U is utilization, and X is throughput.
    
    Args:
        csv_file_path (str): Path to the CSV file with metrics in the format (metric, value, id, type)
    
    Returns:
        dict: A dictionary with entry IDs as keys and estimated service times as values
    """
    # Check if file exists
    if not os.path.exists(csv_file_path):
        print(f"Error: CSV file not found at {csv_file_path}")
        return None
    
    try:
        # Read the CSV into a pandas DataFrame for easier manipulation
        df = pd.read_csv(csv_file_path)
        
        # Check if the CSV has the expected format
        required_columns = ['metric', 'value', 'id', 'type']
        if not all(col in df.columns for col in required_columns):
            print(f"Error: CSV file does not have the expected columns: {required_columns}")
            print(f"Found columns: {df.columns.tolist()}")
            return None
        
        # Extract utilization data (for processors)
        util_df = df[df['metric'] == 'utilization']
        
        # Extract throughput data (for entries)
        throughput_df = df[df['metric'] == 'throughput']
        
        # Get the list of entries
        entries = throughput_df['id'].unique()
        
        # Dictionary to store the results
        service_times = {}
        
        # For each entry, calculate its service time
        for entry in entries:
            # Get the throughput for this entry
            entry_throughput = throughput_df[throughput_df['id'] == entry]['value'].values[0]
            
            # For the utilization, we need to find which processor hosts this entry
            # This is trickier as the CSV doesn't directly map entries to processors
            # We need to use some heuristics or additional information
            
            # Approach: Extract the processor number from the entry name
            # This assumes entries and processors follow a naming convention like Entry1 for Processor1
            try:
                # Extract numeric part from entry name, assuming format like "EntryX"
                entry_num = ''.join(filter(str.isdigit, entry))
                if not entry_num:
                    print(f"Warning: Could not extract processor number from entry {entry}")
                    continue
                
                # Look for matching processor (e.g., "ProcX")
                proc_pattern = f"Proc{entry_num}"
                matching_procs = util_df[util_df['id'].str.contains(proc_pattern)]
                
                if len(matching_procs) == 0:
                    print(f"Warning: No matching processor found for entry {entry}")
                    continue
                
                proc_utilization = matching_procs['value'].values[0]
                
                # Apply utilization law: S = U / X
                service_time = proc_utilization / entry_throughput
                service_times[entry] = service_time
                
                print(f"Entry: {entry}, Throughput: {entry_throughput}, Utilization: {proc_utilization}, Service Time: {service_time}")
                
            except Exception as e:
                print(f"Error processing entry {entry}: {e}")
        
        return service_times
    
    except Exception as e:
        print(f"Error processing CSV file: {e}")
        return None

def save_service_times(service_times, output_path=None, input_csv_path=None):
    """
    Saves the estimated service times to a CSV file.
    
    Args:
        service_times (dict): Dictionary with entry IDs as keys and service times as values
        output_path (str): Path to save the output CSV (if None, creates based on input path)
        input_csv_path (str): Path to the input CSV (used to create output path if not provided)
    
    Returns:
        str: Path to the created CSV file or None if error
    """
    if not service_times:
        print("No service times to save")
        return None
    
    try:
        # Create output path if not provided
        if not output_path and input_csv_path:
            base_path = os.path.splitext(input_csv_path)[0]
            output_path = f"{base_path}_service_times.csv"
        elif not output_path:
            output_path = "estimated_service_times.csv"
        
        # Create DataFrame from the dictionary
        df = pd.DataFrame(list(service_times.items()), columns=['entry', 'service_time'])
        
        # Save to CSV
        df.to_csv(output_path, index=False)
        print(f"Service times saved to: {output_path}")
        return output_path
    
    except Exception as e:
        print(f"Error saving service times: {e}")
        return None

def main():
    """Parse command-line arguments and run the service time estimator."""
    parser = argparse.ArgumentParser(description="Estimate service times from LQN solver CSV results using utilization law.")
    
    parser.add_argument("csv_file", help="Path to the input CSV file with LQN solver results.")
    parser.add_argument("--output", "-o", help="Path to save the output CSV file (optional).")
    
    args = parser.parse_args()
    
    print(f"Input CSV file: {args.csv_file}")
    
    # Estimate service times
    service_times = estimateServiceTime(args.csv_file)
    
    if service_times:
        print(f"Estimated service times for {len(service_times)} entries")
        
        # Save to CSV
        output_path = save_service_times(service_times, args.output, args.csv_file)
        if output_path:
            print(f"Results saved to: {output_path}")
    else:
        print("Failed to estimate service times")

if __name__ == "__main__":
    main() 