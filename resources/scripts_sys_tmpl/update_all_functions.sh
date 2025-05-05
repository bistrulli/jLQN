#! /bin/bash

# Store command-line arguments in an array
args=("$@")

# Check if any arguments were provided
if [ ${#args[@]} -eq 0 ]; then
    echo "Error: No concurrency values provided."
    echo "Usage: $0 <concurrency_E1> <concurrency_E2> ... <concurrency_EN>"
    exit 1
fi

# Find all directories starting with "Entr" followed by a digit (excluding Entr0),
# only in the current directory (-maxdepth 1),
# sort them numerically (sort -V for version sort),
# and store them in the 'dirs' array.
mapfile -t dirs < <(find . -maxdepth 1 -type d -name 'Entr[1-9]*' | sort -V)

# Check if the number of found directories matches the number of arguments
num_dirs=${#dirs[@]}
num_args=${#args[@]}

if [ "$num_dirs" -ne "$num_args" ]; then
    echo "Error: The number of arguments ($num_args) does not match the number of Entr* directories found ($num_dirs)."
    echo "Found directories (${num_dirs}):"
    printf '  %s\n' "${dirs[@]}" # Print each found directory on a new line
    echo "Provided arguments (${num_args}):"
    printf '  %s\n' "${args[@]}"  # Print each provided argument on a new line
    exit 1
fi

echo "Starting update for $num_dirs functions..."

# Iterate through the indices of the directories array
for index in "${!dirs[@]}"; do
    dir="${dirs[index]}"            # Get the current directory name
    concurrency="${args[index]}"    # Get the corresponding concurrency argument

    update_script="$dir/update.sh"  # Build the path to the update.sh script

    # Check if the update script exists and is a regular file (-f)
    # We no longer check for executable permissions (-x)
    if [ -f "$update_script" ]; then
        echo "--------------------------------------------------"
        # Message indicating execution via sh
        echo "Running with sh: sh $update_script $concurrency 100 1"
        echo "--------------------------------------------------"
        # Execute the update.sh script explicitly using the 'sh' interpreter
        sh "$update_script" "$concurrency" 100 1
        echo "Completed for directory: $dir"
    else
        echo "--------------------------------------------------"
        # Warning message if script is not found or not a regular file
        echo "Warning: Script $update_script not found or is not a regular file in directory $dir. Skipping."
        echo "--------------------------------------------------"
    fi
done

echo "=================================================="
echo "Update process for all functions initiated."
echo "=================================================="

exit 0