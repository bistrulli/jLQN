import csv
import re
import os
from datetime import datetime

def turn_log_into_csv(lqn_name, entr_name):
    # Input log file and output CSV file paths
    log_file_path = f'../output/{lqn_name}/{entr_name}/{entr_name}.log'
    csv_file_path = f'../output/{lqn_name}/{entr_name}/{entr_name}.csv'

    # Regex patterns to match the log entries
    time_pattern = re.compile(r'^(.*) functions\.Logic service')
    info_pattern = re.compile(r'INFO: cpu:=(-?\d+)  rt:=(-?\d+)$')

    with open(log_file_path, 'r') as log_file, open(csv_file_path, 'w', newline='') as csv_file:
        csv_writer = csv.writer(csv_file)
        # Header
        csv_writer.writerow(['date-time', 'cpu', 'rt'])
        
        date_time_formatted = None

        for line in log_file:
            match_time = time_pattern.match(line.strip())
            match_info = info_pattern.match(line.strip())

            if match_time: # e.g. Feb 19, 2025 6:48:46 PM functions.Logic service
                date_time_str = match_time.group(1)
                # Convert the date-time string to a standard format
                date_time = datetime.strptime(date_time_str, '%b %d, %Y %I:%M:%S %p')
                date_time_formatted = date_time.strftime('%Y-%m-%d %H:%M:%S')
                #print(f"Matched time: {date_time_formatted}")

            if match_info and date_time_formatted: # e.g. INFO: cpu:=73258820  rt:=3136098152
                cpu = match_info.group(1)
                rt = match_info.group(2)
                # Write the extracted values to the CSV file
                csv_writer.writerow([date_time_formatted, cpu, rt])
                #print(f"Matched info: cpu={cpu}, rt={rt}")
                date_time_formatted = None

    #print(f'Log file parsed and saved to {csv_file_path}')

def calculate_average(csv_file_path, column_name):
    values = []
    # Open the CSV file and read the values of column column_name
    with open(csv_file_path, 'r') as csv_file:
        csv_reader = csv.DictReader(csv_file)
        for row in csv_reader:
            values.append(int(row[column_name]))
    if values:
        return sum(values) / len(values)
    else:
        return None

def extract_number(entry):
    return int(entry[4:])  # Assuming the format is always 'Entr' followed by a number


def list_entr_folders(directory):
    pattern = re.compile(r'^Entr\d+$')
    return [entry for entry in os.listdir(directory) if os.path.isdir(os.path.join(directory, entry)) and pattern.match(entry)]

def extract_service_times_from_log(lqn_folder_path, lqn_name):
    service_times = []

    # Iterate over all directories in the lqn_name folder, excluding Entr0
    for entr_name in sorted(list_entr_folders(lqn_folder_path), key=extract_number):
        entr_path = os.path.join(lqn_folder_path, entr_name)
        if os.path.isdir(entr_path) and entr_name != 'Entr0':
            turn_log_into_csv(lqn_name, entr_name)

            csv_file_path = f'../output/{lqn_name}/{entr_name}/{entr_name}.csv'

            # Calculate the average rt value
            average_rt = calculate_average(csv_file_path, 'rt')
            #average_cpu = calculate_average(csv_file_path, 'cpu')
            service_time = round(average_rt/1000000)
            service_times.append(service_time) # Convert to milliseconds

    return service_times