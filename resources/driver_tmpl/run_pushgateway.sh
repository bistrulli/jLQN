#!/bin/bash

CONTAINER_NAME="pushgateway"

# Check if the container exists
if sudo docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
    # Check if the container is not running
    if ! sudo docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        echo "Removing existing container '${CONTAINER_NAME}'..."
        sudo docker rm "${CONTAINER_NAME}"
    else
        echo "Container '${CONTAINER_NAME}' is already running."
        exit 0
    fi
fi

# Run the container
echo "Starting container '${CONTAINER_NAME}'..."
sudo docker run -d -p 9091:9091 --name "${CONTAINER_NAME}" prom/pushgateway
