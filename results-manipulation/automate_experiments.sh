#!/bin/bash

# Function to kill processes using ports from 8081 to 8090
kill_processes_on_ports() {
    for port in {8081..8099}; do
        echo "Checking port $port"
        # Find the process ID (PID) using the port
        pid=$(lsof -t -i:$port)
        if [ -n "$pid" ]; then
            echo "Killing process $pid using port $port"
            kill -9 $pid
        else
            echo "No process found using port $port"
        fi
    done
}

# Main script
output_dir="/home/robb/git/jLQN/output"
users=50  # Number of users for Locust
runtime="10m"  # Runtime for Locust

for folder in "$output_dir"/*lqn; do
    if [ -d "$folder" ]; then
        echo "Processing folder: $folder"

        # Kill any processes using ports from 8081 to 8090
        kill_processes_on_ports

        # Deploy the application
        echo "Deploying application in folder: $folder"
        (cd "$folder" && ./deploy_local_sys.sh) &
        deploy_pid=$!
        echo "Application deployed in $folder"

        # Wait for the application to be fully deployed (1 minute should be enough)
        sleep 60

        # Start the Locust swarm
        echo "Starting Locust swarm in folder: $folder/Entr0"
        (cd "$folder/Entr0" && ./profile.sh "$users" "$runtime") &
        locust_pid=$!
        echo "Locust swarm started in $folder"

        # Wait for Locust to finish
        wait $locust_pid
        echo "Locust swarm finished in $folder"

        # Close the application
        kill $deploy_pid
        echo "Application closed in $folder"

        # Clean up any remaining processes
        pkill -f deploy_local_sys.sh
        pkill -f profile.sh

        sleep 30
    fi
done