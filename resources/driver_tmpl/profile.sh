#! /bin/sh

EXPERIMENT_NAME=$1
N_USERS=$2
DURATION=$3
EXEC_TYPE=$4
UTILIZATION=$5 # The level of utilization used by WasteLess

# Get the parent directory of this script
script_dir=$(dirname "$(realpath "$0")")
parent_dir=$(dirname "$script_dir")

# Get all Entr[0-9]+ directories that are siblings of the parent directory and extract only their names
entr_dirs=$(find "$parent_dir" -maxdepth 1 -type d -regex ".*/Entr[0-9]+" | sort -V | xargs -n1 basename)

# Create the "experiments" folder if it does not exist
output_dir="experiments"
if [ ! -d "$output_dir" ]; then
    mkdir "$output_dir"
    echo "Created directory: $output_dir"
fi

# Run Locust and save output files in the "experiments" folder
if [ "$EXEC_TYPE" = "fixed" ]; then
    locust --headless --csv ${output_dir}/${EXPERIMENT_NAME} -f SimpleWorkload.py --users $N_USERS --run-time=${DURATION}m --host=$protocol://$region-$project.cloudfunctions.net/
else
    locust --headless --csv ${output_dir}/${EXPERIMENT_NAME} -f SimpleWorkload.py,traceShape.py --users $N_USERS --host=$protocol://$region-$project.cloudfunctions.net/
fi

# Export metrics to a CSV file in the "experiments" folder
if [ -z "$UTILIZATION" ]; then
    python export_function_metrics.py --minutes ${DURATION} --output ${output_dir}/${EXPERIMENT_NAME}_metrics.csv --entries $entr_dirs
else
    python export_function_metrics.py --minutes ${DURATION} --output ${output_dir}/${EXPERIMENT_NAME}_metrics.csv --utilization $UTILIZATION --entries $entr_dirs
fi