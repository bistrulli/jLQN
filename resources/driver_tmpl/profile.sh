#! /bin/sh

locust --headless --csv validation -f SimpleWorkload.py  --u $1 --run-time=$2  --host=$protocol://$region-$project.cloudfunctions.net/
#locust --headless --csv validation -f SimpleWorkload.py,traceShape.py  --u $1 --host=$protocol://$region-$project.cloudfunctions.net/

python export_function_metrics.py --minutes $2 --output ${1}u-${2}m_metrics.csv