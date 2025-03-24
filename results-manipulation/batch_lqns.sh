#!/bin/bash

# Directory containing the .lqn files
directory="../resources/wasteless_journal"

# Iterate over all .lqn files in the specified directory
for lqn_file in "$directory"/*.lqn; do
    # Check if there are any .lqn files
    if [ -e "$lqn_file" ]; then
        echo "Processing $lqn_file"
        # Execute the lqns command with the specified options
        lqns "$lqn_file" -P stop-on-message-loss=false --exact-mva
    else
        echo "No .lqn files found in the directory $directory."
        break
    fi
done