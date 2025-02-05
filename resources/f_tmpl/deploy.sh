#! /bin/sh

source ../environment.sh

gcloud beta functions deploy $fname \
--gen2 \
--runtime=java17 \
--region=$REGION_NAME \
--source=. \
--entry-point=functions.Logic \
--memory=1024MB \
--cpu=1 \
--trigger-http \
--allow-unauthenticated \
--project $PROJECT_NAME \
--max-instances=100 \
--min-instances=1
