import re

def extract_service_times_from_lqns(file_path):
    service_times = []
    entry_pattern = re.compile(r'^Task\d+\s+Entr\d+\s+([\d.]+)')
    section_header_pattern = re.compile(r'^Service times:$')
    stop_pattern = re.compile(r'^Service time variance \(per phase\)$')

    with open(file_path, 'r') as file:
        lines = file.readlines()
        in_service_times_section = False

        for line in lines:
            if stop_pattern.match(line.strip()):
                break

            if section_header_pattern.match(line.strip()):
                in_service_times_section = True
                continue

            if in_service_times_section:
                match = entry_pattern.match(line.strip())
                if match:
                    service_time = float(match.group(1))
                    service_times.append(round(service_time*1000)) # Convert to milliseconds
    
    # Remove the first value from the list
    if service_times:
        service_times.pop(0)

    return service_times

# # Example usage
# file_path = '/home/robb/git/jLQN/resources/wasteless_journal/lqn0-10functions.out'
# service_times = extract_service_times_from_lqns(file_path)
# print(service_times)