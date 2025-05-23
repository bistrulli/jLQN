import requests
import csv
import sys
import time
import datetime
import collections
import argparse
from tabulate import tabulate  # For formatting CSV output
import os  # For filesystem operations

PROMETHEUS_URL = 'http://localhost:9090'  # Adjust if Prometheus is hosted elsewhere
OUTPUT_CSV = 'function_metrics.csv'

# Query templates for Prometheus metrics
#QUERY_TEMPLATE_AVG_THROUGHPUT = 'sum(rate(http_requests_total[{duration_m}m])) by (function_name)'
QUERY_TEMPLATE_AVG_THROUGHPUT = 'sum(rate(stackdriver_cloud_run_revision_run_googleapis_com_request_count[{duration_m}m])) by (service_name)' 

#QUERY_TEMPLATE_AVG_RESPONSE_TIME = 'sum(rate(logic_response_time_seconds_sum[{duration_m}m])) by (function_name) / sum(rate(logic_response_time_seconds_count[{duration_m}m])) by (function_name)'
QUERY_TEMPLATE_AVG_RESPONSE_TIME = '(sum by (service_name,configuration_name,revision_name) ( rate(stackdriver_cloud_run_revision_run_googleapis_com_request_latencies_sum[{duration_m}m])) / sum by (service_name,configuration_name,revision_name)(rate(stackdriver_cloud_run_revision_run_googleapis_com_request_latencies_count[{duration_m}m])))/1000.0'

#QUERY_TEMPLATE_AVG_CPU_METRIC = 'sum(rate(logic_cpu_time_seconds_sum[{duration_m}m])) by (function_name)'
QUERY_TEMPLATE_AVG_CPU_METRIC = 'sum(rate(stackdriver_cloud_run_revision_run_googleapis_com_container_cpu_usage_sum[{duration_m}m])) by (service_name)'

QUERY_TEMPLATE_BILL = 'avg_over_time(stackdriver_cloud_run_revision_run_googleapis_com_container_billable_instance_time[{duration_m}m]) * {duration_s}'

def query_prometheus_instant(prometheus_url, query, evaluation_time=None):
    """Executes a Prometheus instant query and returns the 'result' list or None on error."""
    api_endpoint = f"{prometheus_url.rstrip('/')}/api/v1/query"
    params = {'query': query}
    if evaluation_time:
        params['time'] = evaluation_time
    else:
        evaluation_time = str(time.time())  # Use current time if not provided
        params['time'] = evaluation_time

    formatted_time = datetime.datetime.fromtimestamp(float(evaluation_time)).strftime("%Y-%m-%d %H:%M:%S")

    # Print query details
    query_print = query[:100] + '...' if len(query) > 103 else query
    print(f"Executing Query: {query_print} at time {formatted_time}")
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

def process_results(results_dict, query_results, metric_key, label_key='service_name', entries=None):
    """Processes Prometheus query results and aggregates them into a dictionary."""
    if query_results is None:
        print(f"  Skipping processing for {metric_key} due to query error.")
        return

    for item in query_results:
        labels = item.get('metric', {})
        func_name = labels.get(label_key)

        # Normalize service_name to match function_name format if needed
        if label_key == 'service_name' and func_name:
            func_name = func_name.capitalize()  # Convert 'entr1' to 'Entr1', etc.

        # Skip if entries is provided and func_name is not in entries
        if func_name not in entries:
            continue

        if func_name:
            try:
                value = float(item['value'][1])  # Convert value to float
                results_dict[func_name][metric_key] = value
            except ValueError:
                print(f"  Warning: Could not convert value '{item['value'][1]}' to float for {metric_key} in {func_name}", file=sys.stderr)
            except KeyError:
                print(f"  Warning: Unexpected result format for {metric_key} in {func_name}: {item}", file=sys.stderr)
        else:
            print(f"  Warning: Result for {metric_key} found without '{label_key}' label: {labels}", file=sys.stderr)

def print_csv(file_path):
    """Prints the content of a CSV file in a formatted table."""
    print("\nCSV Content (formatted):")
    try:
        with open(file_path, 'r', encoding='utf-8') as csvfile:
            reader = csv.reader(csvfile)
            rows = list(reader)  # Read all rows into a list
            headers = rows[0]  # First row is the header
            data_rows = rows[1:]  # Remaining rows are data

            # Format the table using tabulate
            table = tabulate(data_rows, headers=headers, tablefmt="grid", floatfmt=".3f")
            print(table)
    except IOError as e:
        print(f"Error reading CSV file '{file_path}': {e}", file=sys.stderr)
    except Exception as e:
        print(f"Unexpected error during CSV reading: {e}", file=sys.stderr)

if __name__ == "__main__":
    # Parse command-line arguments
    parser = argparse.ArgumentParser(description='Executes Prometheus queries and saves results to CSV.')
    parser.add_argument('--minutes', type=int, default=10, help='Time duration in minutes for query ranges (default: 10)')
    parser.add_argument('--output', default=OUTPUT_CSV, help=f'Output CSV filename (default: {OUTPUT_CSV})')
    parser.add_argument('--utilization', type=float, default=0.4, help='Utilization factor for ScaledConc calculation (default: 0.4)')
    parser.add_argument('--entries', nargs='+', help='List of entries to include in the results')
    args = parser.parse_args()

    print(f"Starting Prometheus data export for a duration of {args.minutes} minutes with utilization {args.utilization}...")
    if args.entries:
        print(f"Filtering results for entries: {', '.join(args.entries)}")

    # Calculate query durations
    duration_m = args.minutes
    duration_s = args.minutes * 60

    # Construct Prometheus queries
    query1 = QUERY_TEMPLATE_AVG_THROUGHPUT.format(duration_m=duration_m)
    query2 = QUERY_TEMPLATE_AVG_RESPONSE_TIME.format(duration_m=duration_m)
    query3 = QUERY_TEMPLATE_AVG_CPU_METRIC.format(duration_m=duration_m)
    query4 = QUERY_TEMPLATE_BILL.format(duration_m=duration_m, duration_s=duration_s)

    # Execute Prometheus queries
    current_eval_time = str(time.time())
    throughput_results = query_prometheus_instant(PROMETHEUS_URL, query1, current_eval_time)
    response_time_results = query_prometheus_instant(PROMETHEUS_URL, query2, current_eval_time)
    cpu_metric_results = query_prometheus_instant(PROMETHEUS_URL, query3, current_eval_time)
    bill_results = query_prometheus_instant(PROMETHEUS_URL, query4, current_eval_time)

    # Aggregate query results
    aggregated_results = collections.defaultdict(dict)
    print("\nProcessing results...")
    process_results(aggregated_results, throughput_results, 'throughput', label_key='service_name', entries=args.entries)
    process_results(aggregated_results, response_time_results, 'avg_response_time', label_key='service_name', entries=args.entries)
    process_results(aggregated_results, cpu_metric_results, 'cpu_metric_result', label_key='service_name', entries=args.entries)
    process_results(aggregated_results, bill_results, 'bill', label_key='service_name', entries=args.entries)

    # Write results to CSV
    csv_headers = ['Func', 'RPS', 'RT', 'CPU', 'BILL', 'Conc', 'ScaledConc']
    print(f"\nWriting aggregated data to '{args.output}'...")
    try:
        with open(args.output, 'w', newline='', encoding='utf-8') as csvfile:
            writer = csv.writer(csvfile)
            writer.writerow(csv_headers)  # Write header

            # Sort function names for consistent output order
            sorted_function_names = sorted(aggregated_results.keys())

            for func_name in sorted_function_names:
                data = aggregated_results[func_name]
                rps = data.get('throughput', None)
                rt = data.get('avg_response_time', None)
                cpu = data.get('cpu_metric_result', None)
                bill = data.get('bill', None)

                # Calculate "Conc" if all required metrics are available
                try:
                    conc = (float(rps) * float(rt)) / float(cpu) if rps and rt and cpu else ''
                except ZeroDivisionError:
                    conc = ''  # Handle division by zero gracefully

                # Calculate "ScaledConc" using the utilization argument
                try:
                    scaled_conc = max(1, round(float(conc) * args.utilization)) if conc else ''
                except ValueError:
                    scaled_conc = ''  # Handle invalid Conc gracefully

                # Write row to CSV
                writer.writerow([func_name, rps, rt, cpu, bill, conc, scaled_conc])

        print(f"Successfully wrote {len(aggregated_results)} rows to '{args.output}'.")

    except IOError as e:
        print(f"Error writing CSV file '{args.output}': {e}", file=sys.stderr)
        sys.exit(1)
    except Exception as e:
        print(f"Unexpected error during CSV writing: {e}", file=sys.stderr)
        sys.exit(1)

    print("Script finished.")

    # Print the CSV content in a formatted table
    print_csv(args.output)