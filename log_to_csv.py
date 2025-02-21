import csv
import re
from datetime import datetime


def turn_log_into_csv(lqn_name, entr_name):
    # Input log file and output CSV file paths
    log_file_path = f'/home/robb/git/jLQN/output/{lqn_name}/{entr_name}/{entr_name}.log'
    csv_file_path = f'/home/robb/git/jLQN/output/{lqn_name}/{entr_name}/{entr_name}.csv'

    # Regex patterns to match the log entries
    time_pattern = re.compile(r'^(.*) functions\.Logic service')
    info_pattern = re.compile(r'INFO: cpu:=(\d+)  rt:=(\d+)$')

    with open(log_file_path, 'r') as log_file, open(csv_file_path, 'w', newline='') as csv_file:
        csv_writer = csv.writer(csv_file)
        # Header
        csv_writer.writerow(['date-time', 'cpu', 'rt'])
        
        for line in log_file:
            match_time = time_pattern.match(line.strip())
            match_info = info_pattern.match(line.strip())

            if match_time: # e.g. Feb 19, 2025 6:48:46 PM functions.Logic service
                date_time_str = match_time.group(1)
                # Convert the date-time string to a standard format
                date_time = datetime.strptime(date_time_str, '%b %d, %Y %I:%M:%S %p')
                date_time_formatted = date_time.strftime('%Y-%m-%d %H:%M:%S')

            if match_info: # e.g. INFO: cpu:=73258820  rt:=3136098152
                cpu = match_info.group(1)
                rt = match_info.group(2)
                # Write the extracted values to the CSV file
                csv_writer.writerow([date_time_formatted, cpu, rt])

    print(f'Log file parsed and saved to {csv_file_path}')

for i in range(1, 5):
    turn_log_into_csv('5fun-lqn0-template.lqn', f'Entr{i}')