sudo docker stop stackdriver-exporter && sudo docker rm stackdriver-exporter

sudo docker run -d \
    --name stackdriver-exporter \
    -p 9255:9255 \
    --restart=unless-stopped \
    --network prometheus_network \
    prometheuscommunity/stackdriver-exporter:latest \
    --google.project-ids="$GCP_PROJECT_ID" \
    --monitoring.metrics-prefixes="run.googleapis.com/container/instance_count" \
    --monitoring.filters='resource.type = "cloud_run_revision"' \
    --web.listen-address=":9255"