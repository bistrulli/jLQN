#! /bin/sh

gcloud beta functions deploy $fname \
--gen2 \
--runtime=java17 \
--region=$region \
--source=. \
--entry-point=functions.Logic \
--memory=1024MB \
--cpu=1 \
--trigger-http \
--allow-unauthenticated \
--project $project \
--max-instances=100 \
--min-instances=1
