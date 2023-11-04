#! /bin/sh

#https://cloud.google.com/sdk/gcloud/reference/run/services/update#--max-instances
gcloud run services update f1 --concurrency=3 --project=modellearning --region=northamerica-northeast1 --max-instances=20 --min-instances=0 
