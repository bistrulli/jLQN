#! /bin/sh

EXPERIMENT_NAME=$1
N_USERS=$2
DURATION=$3

# Create the "experiments" folder if it does not exist
output_dir="experiments"
if [ ! -d "$output_dir" ]; then
    mkdir "$output_dir"
    echo "Created directory: $output_dir"
fi

# Run Locust and save output files in the "experiments" folder
locust --headless --csv ${output_dir}/${EXPERIMENT_NAME} -f SimpleWorkload.py --users $N_USERS --run-time=${DURATION}m --host=$protocol://$region-$project.cloudfunctions.net/

# Export metrics to a CSV file in the "experiments" folder
python export_function_metrics.py --minutes ${DURATION} --output ${output_dir}/${EXPERIMENT_NAME}_metrics.csv