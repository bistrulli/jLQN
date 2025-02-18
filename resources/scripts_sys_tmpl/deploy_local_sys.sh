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