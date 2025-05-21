# Working on the Project

Follow these steps to set up and work on the project:

1. **Start the Compute Engine and Authenticate with Google Cloud:**
   Ensure the Compute Engine is running and authenticate with Google Cloud using the appropriate credentials.

2. **Launch Prometheus:**
   Run the Prometheus monitoring service:
   ```bash
   ./prometheus/run_prometheus.sh
   ```

3. **Activate the Python Virtual Environment:**
   Activate the virtual environment to ensure the correct dependencies are used:
   ```bash
   source ~/jLQN/.venv/bin/activate
   ```

You are now ready to work on the code.

---

## Adding Metrics to Stackdriver

To add new metrics to Stackdriver, follow these steps:

1. **Stop and Remove Existing Docker Containers:**
   Terminate any running `stackdriver-exporter` containers:
   ```bash
   sudo docker stop stackdriver-exporter
   sudo docker rm stackdriver-exporter
   ```

2. **Run the Stackdriver Exporter with Updated Metrics Prefixes:**
   Restart the exporter with the necessary prefixes:
   ```bash
   sudo docker run -d \
       --name stackdriver-exporter \
       -p 9255:9255 \
       --restart=unless-stopped \
       --network prometheus_network \
       prometheuscommunity/stackdriver-exporter:latest \
       --google.project-ids="$GCP_PROJECT_ID" \
       --monitoring.metrics-prefixes="run.googleapis.com/container/instance_count" \
       --monitoring.metrics-prefixes="run.googleapis.com/container/billable_instance_time" \
       --monitoring.metrics-prefixes="run.googleapis.com/request_count" \
       --monitoring.metrics-prefixes="run.googleapis.com/container/cpu/usage" \
       --monitoring.metrics-prefixes="run.googleapis.com/container/cpu/utilizations" \
       --monitoring.metrics-prefixes="run.googleapis.com/container/cpu/allocation_time" \
       --monitoring.metrics-prefixes="run.googleapis.com/request_latencies" \
       --monitoring.filters='resource.type = "cloud_run_revision"' \
       --monitoring.metrics-interval="1m" \
       --monitoring.aggregate-deltas \
       --web.listen-address=":9255"
   ```

   Add additional prefixes as needed by appending them to the `--monitoring.metrics-prefixes` flags.

---

## Generating LQNs and Creating Function Networks

1. **Random LQN Generator:**
   The `random-LQN-generator` folder contains all the resources needed to generate LQNs.

2. **Creating a Function Network in Java:**
   Place the relevant LQN file in the `resources/wasteless_journal` folder and execute:
   ```bash
   java -jar ~/jLQN/target/jLQN-0.0.1-SNAPSHOT.jar --project syda-serverless-wless -r us-central1 -pi 34.67.45.208
   ```

3. **Working with the Generated Function Network:**
   The generated function network will be located in the `output` folder.

---

## Deploying and Updating Functions

1. **Deploying Functions with `deploy_sys`:**
   The `deploy_sys` script removes existing functions using `Entr0/delete_gcf_functions.sh` and deploys new ones. Refer to the `deploy.sh` file in each function's folder for deployment parameters.

2. **Updating Function Concurrency with `update_all_functions`:**
   The `update_all_functions` script adjusts the concurrency of all functions. It accepts concurrency values for `Entr1`, `Entr2`, etc., in order. The script defines maximum and minimum instance values (1-100).

---

## Running Experiments

1. **Running Experiments with `run_experiments.sh`:**
   The `run_experiments.sh` script, located in the `Entr0` folder, automates experiments with algorithms (`NC`, `GCR`, `WL`). Results are saved in the `experiments` folder. Use `compare_algorithms.py` to compare results. Files must follow the naming convention `NC_...`, `GCR_...`, and `WL_...` for comparison.

---

## Input Arguments for Scripts

1. **Input Arguments for `profile.sh` and `run_experiments.sh`:**
Both `profile.sh` and `run_experiments.sh` accept several input arguments. Depending on the configuration, not all arguments are actually used. This approach could be improved in the future. Below are the current input arguments:

   - `profile.sh`:
     - **Experiment Name**: The name of the experiment (use `WL`, `GCR`, or `NC` for compatibility with `compare_algorithms.py`).
     - **Number of Users**: The number of users to simulate (used only for fixed execution type).
     - **Duration**: The experiment duration in minutes (used only for fixed execution type).
     - **Execution Type**: The type of execution (`fixed` or other).
     - **Utilization**: The utilization level for WasteLess (used for calibration).

   - `run_experiments.sh`:
     - **N_USERS**: The number of users to simulate (actually useless, since it's defined by the traceShape file).
     - **DURATION**: The duration of the experiment in seconds (actually useless, since it's defined by the traceShape file).
     - **UTILIZATION**: The level of utilization used by WasteLess (used to calculate the concurrency during the calibration).

---

