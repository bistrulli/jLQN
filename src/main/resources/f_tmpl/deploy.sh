#! /bin/sh
gcloud beta functions deploy $fname \
--gen2 \
--runtime=java17 \
--region=northamerica-northeast1 \
--source=. \
--entry-point=functions.LogicF1 \
--memory=512MB \
--cpu=1 \
--trigger-http \
--allow-unauthenticated \
--project modellearning \
--max-instances=50 \
--min-instances=1
