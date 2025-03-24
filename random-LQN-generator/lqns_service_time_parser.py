import re

def get_tasks_and_service_times(filepath):
    with open(filepath, 'r') as file:
        lines = file.readlines()

    task_service_times = {}
    current_task = None
    for line in lines:
        line = line.strip()
        if line.startswith('A Task'):
            current_task = int(line.split()[1][4:])  # Extract the task number
        elif line.startswith('s acti') and len(line.split()[1]) == 5:
            parts = line.split()
            service_time = float(parts[2])
            if current_task is not None:
                if current_task not in task_service_times:
                    task_service_times[current_task] = 0
                task_service_times[current_task] += service_time

    return task_service_times

# Example usage
filepath = './LQNs/20250318/lqn01-5f.lqn'
task_service_times = get_tasks_and_service_times(filepath)
for task, service_time in task_service_times.items():
    print(f"Task {task} has a service time of {service_time}")