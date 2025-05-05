#!/bin/bash

# Check if 3 arguments have been passed
if [ "$#" -ne 3 ]; then
    echo "Usage: $0 <arg1> <arg2> <arg3>" >&2 # Send errors to stderr
    exit 1
fi

# Store the arguments
ARG1=$1
ARG2=$2
ARG3=$3

echo "Updating system components with arguments: $ARG1 $ARG2 $ARG3"

# Get the directory where this script is located
script_dir=$(dirname "$(realpath "$0")")
echo "Script directory: $script_dir"

# Flag to check if we found and executed something
executed_something=0

# Change to the script directory to facilitate searching for EntrX siblings
cd "$script_dir" || { echo "Error: Could not cd to script directory '$script_dir'" >&2; exit 1; }

echo "Searching for Entr*/update.sh in $PWD"

# Explicitly iterate over Entr[1-9]* directories (as update_all_functions.sh did)
# This is safer than a recursive find if the structure is known.
for dir in Entr[1-9]*; do
    # Check if it is actually a directory found by globbing
    if [ -d "$dir" ]; then
        update_script="$dir/update.sh"
        # Check if the update.sh file exists inside the directory
        if [ -f "$update_script" ]; then
            echo "Found script: $update_script"
            echo "Executing $update_script with args $ARG1 $ARG2 $ARG3"
            # Execute the script passing the arguments
            sh "$update_script" "$ARG1" "$ARG2" "$ARG3"
            # Check the execution result
            if [ $? -ne 0 ]; then
                echo "Error: Execution failed for $update_script" >&2
                # You might want to exit here with 'exit 1' if a failure is critical
            else
                echo "Successfully executed $update_script"
                executed_something=1
            fi
        # else
            # echo "Debug: update.sh not found in $dir" # Uncomment for debugging
        fi
    # else
        # echo "Debug: '$dir' matched by globbing is not a directory." # Uncomment for debugging
    fi
done

# Return to the original directory from which the script was called (optional, good practice)
cd - > /dev/null

if [ $executed_something -eq 0 ]; then
    echo "Warning: No Entr[1-9]*/update.sh scripts were found or executed in $script_dir." >&2
fi

echo "Finished updating system components."
exit 0
