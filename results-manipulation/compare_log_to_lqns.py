import extract_service_times_from_lqns as estfl
import log_to_csv as ltc
import argparse
import csv

def get_cli():
    """
    Get input arguments from CLI.
    :return:    ArgumentParser object.
    """
    parser = argparse.ArgumentParser(description="Generate a csv comparing results obtained via lqns and logging.")
    
    # Number of LQNs
    parser.add_argument("-f", "--filename", type=str, help='The name of the lqn (excluding extension .lqn)', required=True)
    
    return parser.parse_args()

if __name__ == '__main__':
    args = get_cli()

    lqn_name = args.filename

    lqns_out_path = f'/home/robb/git/jLQN/resources/wasteless_journal/{lqn_name}.out'
    lqns_service_times = estfl.extract_service_times_from_lqns(lqns_out_path)
    #print(f"Service times extracted from {lqn_name}.out:\n{lqns_service_times}")

    lqn_path = f'../output/{lqn_name}.lqn'
    log_service_times = ltc.extract_service_times_from_log(lqn_path, f"{lqn_name}.lqn")
    #print(f"Service times of {lqn_name} extracted from log:\n{log_service_times}")

    # Ensure both lists have the same length
    min_length = min(len(lqns_service_times), len(log_service_times))
    lqns_service_times = lqns_service_times[:min_length]
    log_service_times = log_service_times[:min_length]

    # Calculate relative error percentage
    relative_errors = []
    for lqns_time, log_time in zip(lqns_service_times, log_service_times):
        relative_error = abs(lqns_time - log_time) / log_time
        relative_errors.append(relative_error)

    # Calculate average relative error
    average_relative_error = sum(relative_errors) / len(relative_errors) if relative_errors else 0
    print(f"{lqn_name}: {round(average_relative_error*100,2)}%") #Average Relative Error: 

    # Generate CSV file
    csv_file_path = f'/home/robb/git/jLQN/results-manipulation/comparison/{lqn_name}_comparison.csv'
    with open(csv_file_path, 'w', newline='') as csv_file:
        csv_writer = csv.writer(csv_file)
        csv_writer.writerow(['LQNS', 'LOGS', 'ERROR'])
        for lqns_time, log_time, relative_error in zip(lqns_service_times, log_service_times, relative_errors):
            csv_writer.writerow([lqns_time, log_time, round(relative_error,4)])

    #print(f"Comparison CSV file generated at {csv_file_path}")