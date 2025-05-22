#!/bin/bash

# Function to find and execute all deploy_sys.sh scripts recursively
deploy_all_systems() {
    # Check if directory parameter is provided
    if [ $# -eq 0 ]; then
        echo "Error: Directory parameter is required"
        echo "Usage: deploy_all_systems <directory>"
        return 1
    fi

    local search_dir="$1"
    local log_file="$search_dir/deploy_progress.txt"
    
    # Check if directory exists
    if [ ! -d "$search_dir" ]; then
        echo "Error: Directory '$search_dir' does not exist"
        return 1
    fi
    
    echo "Searching for deploy_sys.sh scripts in $search_dir..."
    echo "Logging completed deployments to: $log_file"

    # Create log file if it doesn't exist
    touch "$log_file" || {
        echo "Warning: cannot write to log file '$log_file'"
    }

    # Store the current directory to return to it later
    local original_dir="$PWD"

    # Find only deploy_sys.sh in directories matching lqn[0-9]+-[0-9]+f.lqn
    find "$search_dir" \
        -regextype posix-extended \
        -type f \
        -regex ".*/lqn[0-9]+-[0-9]+f\.lqn/deploy_sys\.sh$" \
        -print0 \
    | while IFS= read -r -d '' script_path; do
        # Get the parent directory of the script
        local script_dir="$(dirname "$script_path")"
        local script_name="$(basename "$script_path")"
        
        echo "Executing $script_path"
        
        # Change to the script's directory and execute
        if cd "$script_dir"; then
            chmod +x "$script_name"
            if ./"$script_name"; then
                # If execution is successful, log it
                echo "$script_path" >> "$log_file"
                echo "=> Logged: $script_path"
            else
                echo "Warning: execution of $script_path failed, not logged."
            fi
            # Return to original directory
            cd "$original_dir"
        else
            echo "Error: Could not change to directory $script_dir"
        fi
    done

    echo "Done. See $log_file for the list of completed deployments."
}

# Usage example:
# deploy_all_systems /path/to/dir  # Search in specific directory 