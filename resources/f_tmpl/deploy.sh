#! /bin/sh

gcloud beta functions deploy $fname \
--gen2 \
--runtime=java17 \
--region=$region \
--source=. \
--entry-point=functions.Logic \
--memory=1024MB \
--cpu=3 \
--trigger-http \
--no-allow-unauthenticated \
--service-account="$EXPERIMENT_SA_EMAIL" \
--project $project \
--max-instances=100 \
--min-instances=0
