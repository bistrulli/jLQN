#! /bin/sh

locust --headless --csv validation -f SimpleWorkload.py  --u $1 --run-time=$2  --host=$protocol://$region-$project.cloudfunctions.net/
#locust --headless --csv validation -f SimpleWorkload.py,traceShape.py  --u $1 --host=$protocol://$region-$project.cloudfunctions.net/