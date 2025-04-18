#! /bin/sh

# List all functions in the current project and region
echo "Fetching list of functions..."
functions=$(gcloud functions list --format="value(name)")

if [ -z "$functions" ]; then
  echo "No functions found in the current project."
  exit 0
fi

# Loop through each function and delete it
echo "Deleting all functions..."
for function in $functions; do
  echo "Deleting function: $function"
  gcloud functions delete "$function" --quiet
done

echo "All functions have been deleted."