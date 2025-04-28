#!/bin/bash

# Step 1: Install Python dependencies
echo "Installing Python dependencies..."
sudo pip install -r requirements.txt

# Step 2: Execute the Prometheus script
echo "Deploying Prometheus and Prometheus Pushgateway..."
./prometheus/run_prometheus.sh