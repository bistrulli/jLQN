#! /bin/sh

#https://cloud.google.com/sdk/gcloud/reference/run/services/update#--max-instances
gcloud run services update $fname \
--concurrency=$1 \
--project=modellearning \
--region=northamerica-northeast1 \
--max-instances=$2 \
--min-instances=$3 
