#!/bin/bash

# Function to find and run compare_algorithms.py in a directory
run_comparison() {
    local dir="$1"
    local script_path="$dir/Entr0/compare_algorithms.py"
    
    if [ -f "$script_path" ]; then
        echo "=================================================="
        echo "Running comparison in: $dir/Entr0"
        echo "=================================================="
        
        # Change to the directory containing the script
        cd "$dir/Entr0" || {
            echo "Error: Could not change to directory '$dir/Entr0'"
            return 1
        }
        
        # Run the comparison script
        python3 compare_algorithms.py
        
        # Check if the script executed successfully
        if [ $? -eq 0 ]; then
            echo "Comparison completed successfully in $dir/Entr0"
        else
            echo "Error: Comparison failed in $dir/Entr0"
        fi
        
        # Return to the original directory
        cd - > /dev/null
    else
        echo "Warning: compare_algorithms.py not found in $dir/Entr0"
    fi
}

# Check if any directories were provided
if [ $# -eq 0 ]; then
    echo "Error: No directories provided"
    echo "Usage: $0 <directory1> [directory2] [directory3] ..."
    exit 1
fi

# Store the original directory
original_dir="$PWD"

# Process each directory provided as an argument
for dir in "$@"; do
    # Check if the directory exists
    if [ ! -d "$dir" ]; then
        echo "Error: Directory '$dir' does not exist"
        continue
    fi
    
    # Get absolute path of the directory
    abs_dir="$(realpath "$dir")"
    
    # Find all lqn[0-9]+-[0-9]+f.lqn directories
    find "$abs_dir" -type d -regextype posix-extended -regex ".*/lqn[0-9]+-[0-9]+f\.lqn" | while read -r lqn_dir; do
        run_comparison "$lqn_dir"
    done
done

echo "All comparisons completed." 