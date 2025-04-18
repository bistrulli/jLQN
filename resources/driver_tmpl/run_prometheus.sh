#!/bin/bash

CONTAINER_NAME="pushgateway"

# Check if the container exists
if sudo docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
    # Check if the container is not running
    if ! sudo docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        echo "Removing existing container '${CONTAINER_NAME}'..."
        sudo docker rm "${CONTAINER_NAME}"
        # Run the container
        echo "Starting container '${CONTAINER_NAME}'..."
        sudo docker run -d -p 9091:9091 --name "${CONTAINER_NAME}" prom/pushgateway
    else
        echo "Container '${CONTAINER_NAME}' is already running."
    fi
fi


# Prima, opzionalmente, crea il volume nominato
# docker volume create prometheus_data

# Run Prometheus
sudo docker run --name prometheus -d \
  -p 9090:9090 \
  -v "$(dirname "$0")/prometheus.yml:/etc/prometheus/prometheus.yml:ro" \
  -v prometheus_data:/prometheus \
  prom/prometheus \
  --config.file=/etc/prometheus/prometheus.yml \
  --storage.tsdb.path=/prometheus \
  --web.enable-lifecycle
