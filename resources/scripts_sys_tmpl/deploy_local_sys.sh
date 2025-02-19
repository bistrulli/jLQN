#!/bin/bash

# Define variables

CONFIG_FILE="nginx_conf.conf"


sudo cp "./$CONFIG_FILE" "/etc/nginx/sites-available/$CONFIG_FILE"
sudo ln -s "/etc/nginx/sites-available/$CONFIG_FILE" "/etc/nginx/sites-enabled/$CONFIG_FILE"
sudo nginx -t

# Check if the configuration test was successful
if [ $? -eq 0 ]; then
    # Restart Nginx to apply the new configuration
    sudo systemctl restart nginx
    echo "Nginx configuration applied and Nginx restarted successfully."
else
    echo "Nginx configuration test failed. Please check the configuration file."
fi


dfiles=$(find . -type f -name "local_run.sh")
port=8081
for d in $dfiles 
do
    echo "Executing $d with port $port"
    # Use dirname to get the base path
    fun_path=$(dirname $(realpath "$d"))
    (
        cd $fun_path
        sh local_run.sh $port
    ) &
    port=$((port + 1))
done

# Wait for all background processes to complete
wait