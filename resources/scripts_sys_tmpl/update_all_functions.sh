#!/bin/bash

# --- Get Script's Location and Original Directory ---
script_dir=$(dirname "$(realpath "$0")")
original_dir=$PWD # Store original directory to return later

# --- Argument Handling ---
# Store command-line arguments in an array
args=("$@")

# Check if any arguments were provided
if [ ${#args[@]} -eq 0 ]; then
    echo "Error: No concurrency values provided." >&2
    echo "Usage: $0 <concurrency_E1> <concurrency_E2> ... <concurrency_EN>" >&2
    # Exit without changing back directory if error occurs early
    exit 1
fi

# --- Change to Script's Directory ---
# This ensures that 'find .' searches in the correct location for Entr* folders
cd "$script_dir" || {
    echo "Error: Could not cd to script directory '$script_dir'" >&2
    # Exit without changing back directory if error occurs early
    exit 1
}
echo "Debug: Changed directory to $PWD to search for functions."

# --- Find Target Directories (Relative to Script Directory) ---
# Find all directories starting with "Entr" followed by a digit (excluding Entr0),
# only in the current directory (which is now the script's dir, -maxdepth 1),
# sort them numerically (sort -V for version sort),
# and store them in the 'dirs' array.
echo "Debug: Searching for Entr[1-9]* directories in $PWD..."
mapfile -t dirs < <(find . -maxdepth 1 -type d -name 'Entr[1-9]*' | sort -V)

# --- Argument and Directory Count Check ---
num_dirs=${#dirs[@]}
num_args=${#args[@]}

# Check if any directories were found
if [ "$num_dirs" -eq 0 ]; then
     echo "Warning: No 'Entr[1-9]*' directories found in $script_dir." >&2
     # Continue, maybe it's okay if no functions match for this specific script run
fi

# Check if the number of directories found matches the number of arguments
if [ "$num_dirs" -ne "$num_args" ]; then
    echo "Error: The number of arguments ($num_args) does not match the number of Entr* directories found ($num_dirs in $script_dir)." >&2
    echo "Found directories (${num_dirs}):" >&2
    # Use "." prefix because find returns relative paths like ./Entr1
    printf '  %s\n' "${dirs[@]}" >&2
    echo "Provided arguments (${num_args}):" >&2
    printf '  %s\n' "${args[@]}" >&2
    cd "$original_dir" # Return to original directory before exiting
    exit 1
fi

# --- Execute Updates ---
if [ "$num_dirs" -gt 0 ]; then
    echo "Starting update for $num_dirs functions..."
    # Iterate through the indices of the directories array
    for index in "${!dirs[@]}"; do
        dir_name="${dirs[index]}"        # Get the directory name (e.g., ./Entr1 relative to script_dir)
        concurrency="${args[index]}"    # Get the corresponding concurrency argument

        # update_script path is now relative to script_dir (current PWD)
        update_script="$dir_name/update.sh"

        # Check if the update script exists and is a regular file (-f)
        if [ -f "$update_script" ]; then
            echo "--------------------------------------------------"
            # Message indicating execution via sh
            echo "Running with sh: sh $update_script $concurrency 100 1"
            echo "--------------------------------------------------"
            # Execute the update.sh script explicitly using the 'sh' interpreter
            sh "$update_script" "$concurrency" 100 1
            echo "Completed for directory: $dir_name"
        else
            echo "--------------------------------------------------"
            # Warning message if script is not found or not a regular file
            echo "Warning: Script $update_script not found or is not a regular file in directory $dir_name. Skipping." >&2
            echo "--------------------------------------------------"
        fi
    done
else
    echo "No function directories found to update."
fi

# --- Cleanup ---
echo "=================================================="
echo "Update process for all functions initiated."
echo "=================================================="

# Return to the original directory from which the script was called
cd "$original_dir" || exit 1 # Exit if cd fails
exit 0
log_step