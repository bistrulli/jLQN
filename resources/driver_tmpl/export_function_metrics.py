import requests
import csv
import sys
import time
import datetime
import collections
import argparse

PROMETHEUS_URL = 'http://localhost:9090' # Adjust if your Prometheus is elsewhere
OUTPUT_CSV = 'function_metrics.csv'

QUERY_TEMPLATE_AVG_THROUGHPUT = 'sum(rate(http_requests_total[{duration_m}m])) by (function_name)' #'rate(http_requests_total[{duration_m}m])'
QUERY_TEMPLATE_AVG_RESPONSE_TIME = 'sum(rate(logic_response_time_seconds_sum[{duration_m}m])) by (function_name) / sum(rate(logic_response_time_seconds_count[{duration_m}m])) by (function_name)' #'rate(logic_cpu_time_seconds_sum[{duration_m}m]) / rate(logic_cpu_time_seconds_count[{duration_m}m])'
QUERY_TEMPLATE_AVG_CPU_METRIC = 'sum(rate(logic_cpu_time_seconds_sum[{duration_m}m])) by (function_name)' #'rate(logic_cpu_time_seconds_sum[{duration_m}m])'


def query_prometheus_instant(prometheus_url, query, evaluation_time=None):
    """Executes an instant query and returns the 'result' list or None on error."""
    api_endpoint = f"{prometheus_url.rstrip('/')}/api/v1/query"
    params = {'query': query}
    if evaluation_time:
        params['time'] = evaluation_time
    else:
        evaluation_time = str(time.time()) # Use current time if not provided
        params['time'] = evaluation_time

    formatted_time = datetime.datetime.fromtimestamp(float(evaluation_time)).strftime("%Y-%m-%d %H:%M:%S")

    # Shorten query for printing if too long
    query_print = query[:100] + '...' if len(query) > 103 else query
    print(f"Executing Query: {query_print} at time {formatted_time}")
    # print(f"  Full Query: {query}") # Uncomment for debugging full query
    print(f"  URL: {api_endpoint}")

    try:
        response = requests.get(api_endpoint, params=params, timeout=30)
        response.raise_for_status()
        data = response.json()

        if data.get('status') != 'success':
            print(f"  Error in Prometheus response: {data.get('errorType')}: {data.get('error')}", file=sys.stderr)
            return None

        if data['data']['resultType'] != 'vector':
            print(f"  Error: Expected resultType 'vector', got '{data['data']['resultType']}' for query: {query_print}", file=sys.stderr)
            return None

        print(f"  Query successful, {len(data['data']['result'])} results.")
        return data['data']['result']

    except requests.exceptions.RequestException as e:
        print(f"  Connection error to Prometheus ({api_endpoint}): {e}", file=sys.stderr)
        return None
    except Exception as e:
        print(f"  Unexpected error during query: {e}", file=sys.stderr)
        return None

def process_results(results_dict, query_results, metric_key):
    """Helper function to add query results to the main dictionary."""
    if query_results is None:
        print(f"  Skipping processing for {metric_key} due to query error.")
        return

    for item in query_results:
        labels = item.get('metric', {})
        func_name = labels.get('function_name')
        if func_name:
            try:
                value = float(item['value'][1]) # Get value as float
                # If function_name not seen before, defaultdict creates a new dict
                results_dict[func_name][metric_key] = value
            except ValueError:
                 print(f"  Warning: Could not convert value '{item['value'][1]}' to float for {metric_key} in {func_name}", file=sys.stderr)
            except KeyError:
                 print(f"  Warning: Unexpected result format for {metric_key} in {func_name}: {item}", file=sys.stderr)
        else:
            # Handle results that might not have the function_name label
            print(f"  Warning: Result for {metric_key} found without 'function_name' label: {labels}", file=sys.stderr)


if __name__ == "__main__":
    # --- Argument Parsing ---
    parser = argparse.ArgumentParser(description='Executes specific Prometheus instant queries for multiple functions and saves results to CSV.')
    parser.add_argument('--minutes', type=int, default=10, help='Time duration in minutes for query ranges (default: 10)')
    parser.add_argument('--output', default=OUTPUT_CSV, help=f'Output CSV filename (default: {OUTPUT_CSV})')
    
    # Add back other arguments if needed later, e.g.:
    # parser.add_argument('--url', default=PROMETHEUS_URL, help=f'Prometheus server URL (default: {PROMETHEUS_URL})')
    # parser.add_argument('--eval-time', default=None, help='Evaluation timestamp (Unix format or RFC3339). Default: current time.')

    args = parser.parse_args()
    # --- End Argument Parsing ---

    print(f"Starting Prometheus data export for a duration of {args.minutes} minutes...")

    # Calculate durations for queries
    duration_m = args.minutes
    duration_s = args.minutes * 60

    # Construct queries dynamically
    query1 = QUERY_TEMPLATE_AVG_THROUGHPUT.format(duration_m=duration_m)
    query2 = QUERY_TEMPLATE_AVG_RESPONSE_TIME.format(duration_m=duration_m)
    query3 = QUERY_TEMPLATE_AVG_CPU_METRIC.format(duration_m=duration_m)

    # Use current time for evaluation
    current_eval_time = str(time.time())

    # Execute the queries
    throughput_results = query_prometheus_instant(PROMETHEUS_URL, query1, current_eval_time)
    response_time_results = query_prometheus_instant(PROMETHEUS_URL, query2, current_eval_time)
    cpu_metric_results = query_prometheus_instant(PROMETHEUS_URL, query3, current_eval_time)

    # Aggregate results by function_name
    aggregated_results = collections.defaultdict(dict)

    print("\nProcessing results...")
    process_results(aggregated_results, throughput_results, 'throughput')
    process_results(aggregated_results, response_time_results, 'avg_response_time')
    process_results(aggregated_results, cpu_metric_results, 'cpu_metric_result') # Matches the 3rd query

    # Prepare for CSV writing
    csv_headers = ['Func ', ' RPS ', ' RT  ', ' CPU']

    print(f"\nWriting aggregated data to '{args.output}'...")
    try:
        with open(args.output, 'w', newline='', encoding='utf-8') as csvfile:
            writer = csv.writer(csvfile)
            writer.writerow(csv_headers) # Write header

            # Sort function names for consistent output order
            sorted_function_names = sorted(aggregated_results.keys())

            for func_name in sorted_function_names:
                data = aggregated_results[func_name]
                # Write row, using .get() with default '' if a metric wasn't found for a function
                writer.writerow([
                    func_name,
                    data.get('throughput', ''),
                    data.get('avg_response_time', ''),
                    data.get('cpu_metric_result', '')
                ])
        print(f"Successfully wrote {len(aggregated_results)} rows to '{args.output}'.")

        

    except IOError as e:
        print(f"Error writing CSV file '{args.output}': {e}", file=sys.stderr)
        sys.exit(1)
    except Exception as e:
        print(f"Unexpected error during CSV writing: {e}", file=sys.stderr)
        sys.exit(1)

    print("Script finished.")

    # --- Print CSV content in a human-readable way ---
    print("\nCSV Content (rounded):")
    try:
        with open(args.output, 'r', encoding='utf-8') as csvfile:
            reader = csv.reader(csvfile)
            headers = next(reader)  # Read the header row
            print(f"{' | '.join(headers)}")  # Print headers

            for row in reader:
                rounded_row = [
                    f"{float(value):.3f}" if value.replace('.', '', 1).isdigit() else value
                    for value in row
                ]
                print(f"{' | '.join(rounded_row)}")
    except IOError as e:
        print(f"Error reading CSV file '{args.output}': {e}", file=sys.stderr)
    except Exception as e:
        print(f"Unexpected error during CSV reading: {e}", file=sys.stderr)