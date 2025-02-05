#! /bin/sh

source ../environment.sh

#https://cloud.google.com/sdk/gcloud/reference/run/services/update#--max-instances
gcloud run services update $fname \
--concurrency=$1 \
--project=$PROJECT_NAME \
--region=$REGION_NAME \
--max-instances=$2 \
--min-instances=$3 
