#!/bin/bash

CONTAINER_NAME="pushgateway"
PROMETHEUS_CONTAINER_NAME="prometheus"
NETWORK_NAME="prometheus_network"

# Create the Docker network if it doesn't exist
if ! sudo docker network ls --format '{{.Name}}' | grep -q "^${NETWORK_NAME}$"; then
    echo "Creating Docker network '${NETWORK_NAME}'..."
    sudo docker network create "${NETWORK_NAME}"
else
    echo "Docker network '${NETWORK_NAME}' already exists."
fi

# Stop and remove the Pushgateway container if it exists
if sudo docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
    echo "Stopping and removing container '${CONTAINER_NAME}'..."
    sudo docker stop "${CONTAINER_NAME}" && sudo docker rm "${CONTAINER_NAME}"
fi

# Stop and remove the Prometheus container if it exists
if sudo docker ps -a --format '{{.Names}}' | grep -q "^${PROMETHEUS_CONTAINER_NAME}$"; then
    echo "Stopping and removing container '${PROMETHEUS_CONTAINER_NAME}'..."
    sudo docker stop "${PROMETHEUS_CONTAINER_NAME}" && sudo docker rm "${PROMETHEUS_CONTAINER_NAME}"
fi

# Run Pushgateway
echo "Starting container '${CONTAINER_NAME}'..."
sudo docker run --name "${CONTAINER_NAME}" --network "${NETWORK_NAME}" -d \
  -p 9091:9091 prom/pushgateway

# Run Prometheus
echo "Starting container '${PROMETHEUS_CONTAINER_NAME}'..."
sudo docker run --name "${PROMETHEUS_CONTAINER_NAME}" --network "${NETWORK_NAME}" -d \
  -p 9090:9090 \
  -v "$(dirname "$0")/prometheus.yml:/etc/prometheus/prometheus.yml:ro" \
  -v prometheus_data:/prometheus \
  prom/prometheus \
  --config.file=/etc/prometheus/prometheus.yml \
  --storage.tsdb.path=/prometheus \
  --web.enable-lifecycle