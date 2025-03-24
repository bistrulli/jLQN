#!/bin/bash

# Main script
output_dir="/home/robb/git/jLQN/output"

for folder in "$output_dir"/*lqn; do
    if [ -d "$folder" ]; then
        # Extract the folder name without the trailing ".lqn"
        lqnname=$(basename "$folder" .lqn)
        #echo "Processing folder: $folder with lqnname: $lqnname"

        # Execute the command
        python3 compare_log_to_lqns.py -f "$lqnname"
    fi
done