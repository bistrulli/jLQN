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
    
    # Check if directory exists
    if [ ! -d "$search_dir" ]; then
        echo "Error: Directory '$search_dir' does not exist"
        return 1
    fi
    
    echo "Searching for deploy_sys.sh scripts in $search_dir..."
    
    # Find all deploy_sys.sh files and execute them
    find "$search_dir" -name "deploy_sys.sh" -type f -exec sh -c '
        script_path="{}"
        script_dir="$(dirname "$script_path")"
        script_name="$(basename "$script_path")"
        
        echo "Executing $script_path"
        cd "$script_dir" && chmod +x "$script_name" && ./"$script_name"
    ' \;
    
    echo "All deploy_sys.sh scripts have been executed"
}

# Usage example:
# deploy_all_systems /path/to/dir  # Search in specific directory 