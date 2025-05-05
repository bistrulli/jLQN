#! /bin/sh

EXPERIMENT_NAME=$1
N_USERS=$2
DURATION=$3

locust --headless --csv validation -f SimpleWorkload.py  --users $N_USERS --run-time=${DURATION}m  --host=$protocol://$region-$project.cloudfunctions.net/
#locust --headless --csv validation -f SimpleWorkload.py,traceShape.py  --u $1 --host=$protocol://$region-$project.cloudfunctions.net/

python export_function_metrics.py --minutes ${DURATION} --output ${EXPERIMENT_NAME}_metrics.csv