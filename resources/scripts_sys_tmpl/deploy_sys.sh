#!/bin/bash

./Entr0/delete_gcf_functions.sh

dfiles=$(find . -type f -name "deploy.sh")
for d in $dfiles 
do
	echo "Executing $d"
	# Use dirname to get the base path
	fun_path=$(dirname $(realpath "$d"))
	cd $fun_path
	source deploy.sh
	cd -
done

#run the experiments
cd ./Entr0
./run_experiments.sh 1 10 0.3
cd -