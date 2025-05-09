# Compute Engine Setup

Follow the steps below to set up the compute engine:

## General
### Environment variables
Define the required environment variables:
```bash
echo 'export GCP_PROJECT_ID="your-google-cloud-project-id"' >> ~/.bashrc
echo 'export GCP_ZONE="your-google-cloud-zone"' >> ~/.bashrc
echo 'export GCP_REGION="your-google-cloud-region"' >> ~/.bashrc
echo 'export EXPERIMENT_SA_EMAIL="your-service-account"' >> ~/.bashrc
source ~/.bashrc
echo $GCP_PROJECT_ID
echo $GCP_ZONE
echo $GCP_REGION
echo $EXPERIMENT_SA_EMAIL
```
Replace `your-google-cloud-project-id` with your actual Google Cloud project ID, `your-google-cloud-zone` with your actual Google Cloud zone, and `your-google-cloud-region` with your actual Google Cloud region. If you are not using a Service Account for Google Cloud, there's no need to define `your-service-account`. The last commands verify that the environment variables are correctly set.

### Maven

```bash
sudo apt install maven
```

### Docker
To install Docker, run the script `install_docker.sh`.
```bash
./install_docker.sh
```

## Python

Python is used to run experiments (for example with locust). If you need it, generate a virutal environment and install the required Python packages:

```bash
sudo apt install python3.12-venv
python3 -m venv .ven
sudo pip install -r requirements.txt
```

From now on make sure to be using this virtual environment (unless you are using the random-LQN-generator tool).

```bash
source .venv/bin/activate
```

## Prometheus
Run the `run_prometheus` script located in the `prometheus` folder:
```bash
./prometheus/run_prometheus.sh
```
This script starts the Prometheus monitoring service, which collects and stores metrics data for the compute engine.

### Stackdriver Exporter

Installing Stackdriver Exporter is a little more tricky and requires the following steps.

This guide explains how to deploy the `prometheuscommunity/stackdriver-exporter` as a Docker container to collect metrics from Google Cloud Monitoring (specifically Cloud Run active instance counts) and expose them for a Prometheus instance (also running in Docker) to scrape.


1.  **Create Service Account:**
    A dedicated service account with minimal permissions is recommended for the exporter.
    ```bash
    gcloud iam service-accounts create stackdriver-exporter-sa \
        --display-name="Stackdriver Exporter Service Account" \
        --project=$GCP_PROJECT_ID
    ```

2.  **Assign Monitoring Viewer Role:**
    ```bash
    gcloud projects add-iam-policy-binding $GCP_PROJECT_ID \
        --member="serviceAccount:stackdriver-exporter-sa@${GCP_PROJECT_ID}.iam.gserviceaccount.com" \
        --role="roles/monitoring.viewer"
    ```

3.  **Create and Download Service Account Key:**
    This command downloads the key file (`stackdriver-key.json`) to your current directory.
    ```bash
    gcloud iam service-accounts keys create ./stackdriver-key.json \
        --iam-account="stackdriver-exporter-sa@${GCP_PROJECT_ID}.iam.gserviceaccount.com" \
        --project=$GCP_PROJECT_ID
    ```
    **Important:** Keep this key file secure.

4.  **Copy Key File to Host:** 
Transfer the downloaded key file to the Compute Engine VM (i.e. the host machine where the exporter container will run).
Use `gcloud compute scp` or your preferred secure method. Replace `<YOUR_VM_NAME>` and `<YOUR_VM_ZONE>`.
    ```bash
    gcloud compute scp ./stackdriver-key.json <YOUR_VM_NAME>:/tmp/ --zone=<YOUR_VM_ZONE> --project=$GCP_PROJECT_ID
    ```

5.  **Move and Set Permissions on Host:** SSH into the host machine and move the key to a stable location (e.g., `/etc/prometheus/`) and set appropriate permissions. Read permissions for all (`444`) are needed because the user inside the container needs to read it.
    ```bash
    # SSH into the host machine (e.g., CE VM)

    # On the host machine:
    sudo mv /tmp/stackdriver-key.json /etc/prometheus/stackdriver-key.json
    sudo chmod 444 /etc/prometheus/stackdriver-key.json
    ```

## 3. Run Stackdriver Exporter using Docker

Run the exporter container on the host machine. This command uses the environment variable method for credentials and command-line flags to specify the project and metrics configuration.

6.  **Stop/Remove Previous Attempts (if any):**
    ```bash
    sudo docker stop stackdriver-exporter
    sudo docker rm stackdriver-exporter
    ```

7.  **Run the Exporter Container:** Adjust the host path in the `-v` volume mount if you placed the key file somewhere other than `/etc/prometheus/stackdriver-key.json`.

    ```bash
    sudo docker run -d \
    --name stackdriver-exporter \
    -p 9255:9255 \
    -v "/etc/prometheus/stackdriver-key.json:/etc/stackdriver_exporter/key.json" \
    -e "GOOGLE_APPLICATION_CREDENTIALS=/etc/stackdriver_exporter/key.json" \
    --restart=unless-stopped \
    --network prometheus_network\
    prometheuscommunity/stackdriver-exporter:latest \
    --google.project-ids="$GCP_PROJECT_ID" \
    --monitoring.metrics-prefixes="run.googleapis.com/container/instance_count" \
    --monitoring.filters='resource.type = "cloud_run_revision"' \
    --web.listen-address=":9255"
    ```

8.  **Verify Container Status and Logs:**
    ```bash
    sudo docker ps # Check STATUS is 'Up'
    sudo docker logs stackdriver-exporter # Check for any errors
    ```

## 4. Configure Prometheus Scrape Job

Configure your Prometheus instance (running in Docker) to scrape the newly deployed exporter.

1.  **Edit `prometheus.yml`:** Modify the Prometheus configuration file on the host machine (the path depends on how you mounted it into your Prometheus container, e.g., `/etc/prometheus/prometheus.yml`).

2.  **Add Scrape Configuration:** Add the following job under the `scrape_configs:` section. **Choose the correct target address** based on your Docker networking setup:

    ```yaml
    scrape_configs:
      # ... other jobs ...

      - job_name: 'stackdriver-exporter'
        static_configs:
          # OPTION 1: If Prometheus is on the default 'bridge' network
          # Use Docker's special DNS name to reach the host's mapped port
          - targets: ['host.docker.internal:9255']

          # OPTION 2: If Prometheus and stackdriver-exporter are on the SAME custom Docker network (e.g., 'my-net')
          # Use the exporter container's name as the hostname
          # - targets: ['stackdriver-exporter:9255']

          # OPTION 3: If Prometheus runs with '--network host'
          # Use localhost as it refers to the host machine
          # - targets: ['localhost:9255']
    ```
    *Uncomment the appropriate `targets` line for your setup.*

3.  **Save the `prometheus.yml` file.**

4.  **Reload Prometheus Configuration:** Apply the changes to your running Prometheus container. Replace `<YOUR_PROMETHEUS_CONTAINER_NAME>` with the actual name or ID.

    * **Reload (Recommended, no downtime):**
        ```bash
        sudo docker exec <YOUR_PROMETHEUS_CONTAINER_NAME> kill -HUP 1
        ```
    * **Restart (Alternative, brief downtime):**
        ```bash
        sudo docker restart <YOUR_PROMETHEUS_CONTAINER_NAME>
        ```

## 5. Verification

1.  **Check Prometheus Targets:** Open your Prometheus UI (`http://<HOST_VM_IP>:9090`), navigate to "Status" -> "Targets". The `stackdriver-exporter` job should show the target state as `UP`.
2.  **Query Metrics:** Go to the "Graph" or "Explore" view and execute a query for the metric. The name should be similar to `stackdriver_run_googleapis_com_container_instance_count`. You can find the exact name by checking the `/metrics` endpoint of the exporter (`curl http://localhost:9255/metrics` on the host) or using the autocomplete in the Prometheus UI.
    * To see counts for all functions: `stackdriver_run_googleapis_com_container_instance_count`
    * To filter: `stackdriver_run_googleapis_com_container_instance_count{service_name="<your_function_service_name>"}`

You should now have the active instance count for your GCFv2/Cloud Run services available in Prometheus.

## Notes

- Ensure you have Python and `pip` installed on your system.
- Run the above commands in the root directory of the project.