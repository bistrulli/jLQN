#!/bin/bash

# Add the line to /etc/hosts if it doesn't already exist
HOST_ENTRY="127.0.0.1 $region-$project.cloudfunctions.net"
if ! grep -qF "$HOST_ENTRY" /etc/hosts; then
    echo "$HOST_ENTRY" | sudo tee -a /etc/hosts > /dev/null
    echo "Added $HOST_ENTRY to /etc/hosts"
else
    echo "$HOST_ENTRY already exists in /etc/hosts"
fi

CONFIG_FILE="nginx_conf.conf"

sudo cp -f "./$CONFIG_FILE" "/etc/nginx/sites-available/$CONFIG_FILE"
sudo ln -sf "/etc/nginx/sites-available/$CONFIG_FILE" "/etc/nginx/sites-enabled/$CONFIG_FILE"
sudo nginx -t

# Check if the configuration test was successful
if [ $? -eq 0 ]; then
    # Restart Nginx to apply the new configuration
    sudo systemctl restart nginx
    echo "Nginx configuration applied and Nginx restarted successfully."
else
    echo "Nginx configuration test failed. Please check the configuration file."
fi


# dfiles=$(find . -type f -name "local_run.sh")
# port=8081
# for d in $(echo "$dfiles" | sort)
# do
#     echo "Executing $d with port $port"
#     # Use dirname to get the base path
#     fun_path=$(dirname $(realpath "$d"))
#     (
#         cd $fun_path
#         sh local_run.sh $port
#     ) &
#     port=$((port + 1))
# done

# # Wait for all background processes to complete
# wait


dfiles=$(find . -type f -name "local_run.sh")
for d in $(echo "$dfiles" | sort)
do
    # Extract the number at the end of EntrXX
    entry_dir=$(dirname "$d")
    entry_name=$(basename "$entry_dir")
    entry_number=$(echo "$entry_name" | grep -o -E '[0-9]+$')
    
    # Calculate the port number
    port=$((8080 + entry_number))
    
    echo "Executing $d with port $port"
    # Use dirname to get the base path
    fun_path=$(dirname $(realpath "$d"))
    (
        cd $fun_path
        sh local_run.sh $port
    ) &
done

# Wait for all background processes to complete
wait