import random
import os

out_lqn_folder = os.path.join(os.getcwd(), "LQNs")

def random_service_time():
    return round(random.uniform(0.001, 0.01), 4)

def save_file(filename, text_string):
    with open(f"{out_lqn_folder}/{filename}", "w") as file:
        file.write(text_string)

def header_declaration(filename):
    text = ""
    text += "G\n"
    text += f"\"{filename}\"\n"
    text += "0.01\n"
    text += "10000\n"
    text += "1\n"
    text += "0.5\n"
    text += "-1\n\n"
    return text

def processors_declaration(processors):
    text = ""
    text += "# Processors declaration, with multiplicity\n"
    text += "P 0\n"
    for processor in processors:
        text += f"p {processor} f m 1000\n"
    text += "-1\n\n"
    return text

def tasks_declaration(tasks):
    text = ""
    text += "# Tasks declaration\n"
    text += "T 0\n"
    for index, task in enumerate(tasks):
        if index == 0:
            text += f"t {task} r Entr{index} -1 Proc{index} m 1\n"
        else:
            text += f"t {task} n Entr{index} -1 Proc{index} m 1000\n"
    text += "-1\n\n"
    return text

def entries_declaration(entries):
    text = ""
    text += "# Entries declaration\n"
    text += "E 0\n"
    for index, entry in enumerate(entries):
        text += f"A {entry} acti{index}\n"
    text += "-1\n\n"
    return text

def activities_declaration(tasks, num_activities):
    text = ""
    text += "# Activities declaration\n"
    for i, task in enumerate(tasks):
        text += f"A {task}\n"
        # Service times
        for j in range(num_activities[i]):
            text += f"s acti{i}_{j} {random_service_time()}\n"
        # Calls (activity graph)
        if i != len(tasks)-1:
            for j in range(num_activities[i]):
                #if random.random() < 0.5: # Do it 50% of the times
                if i != len(tasks) and j == 0: # Do it 50% of the times
                    #tgt_entry_num = random.choice([x for x in range(i+1, len(tasks)+1)])
                    tgt_entry_num = i+1
                    tgt_entry = f"Entr{tgt_entry_num}"
                    text += f"y acti{i}_{j} {tgt_entry} 1.0\n:\n"
                    text += f"  acti{i}_{j} -> acti{i}_{j+1}\n"
                    if i != 0:
                        text += f"  acti{i}_{j+1}[Entr{i}]\n"
            text += "-1\n\n"
        else:
            text += f":\n  acti{i}_0[Entr{i}]\n-1\n\n"
    return text



# def tasks_and_entries_declaration(num_tasks, max_entries_per_task):
#   #TODO change moltiplicity
#   tasks_string = ""
#   tasks_string += "# Tasks declaration\n"
#   tasks_string += "T 0\n"

#   entries_string = ""
#   entries_string += "# Entries declaration\n"
#   entries_string += "E 0\n"

#   for i in range(num_tasks):
#       task_name = f"Task{i}"
#       a = "r" if i == 0 else "n"
#       m = "1" if i == 0 else "1000"
#       num_entries = random.randint(1, max_entries_per_task)
#       if max_entries_per_task != 1:
#         entry_names = [f"Entry{i}_{j}" for j in range(num_entries)]
#         for entry_name in entry_names:
#           entries_string += f"s {entry_name} {random_service_time()} -1\n"
#       else:
#         entry_names = [f"Entry{i}"]
#         entries_string += f"A {entry_names[0]} activity{i}\n"
#       tasks_string += f"t {task_name} {a} "
#       tasks_string += " ".join(entry_names) + f" -1 Proc{i} m {m}\n"
#   tasks_string += "-1\n\n"
#   entries_string += "-1\n\n"
#   return tasks_string + entries_string


def generate_random_lqn(filename, num_tasks=5, max_entries_per_task=3, max_activities_per_entry=3):
    processors = [f"Proc{i}" for i in range(num_tasks)]
    tasks = [f"Task{i}" for i in range(num_tasks)]
    entries = [f"Entr{i}" for i in range(num_tasks)]
    num_activities = [random.randint(1, max_activities_per_entry) for i in range(num_tasks)]
    num_activities = [2 for i in range(num_tasks)]
    num_activities[num_tasks-1] = 1

    lqn_text = header_declaration(filename)
    lqn_text += processors_declaration(processors)
    lqn_text += tasks_declaration(tasks)
    lqn_text += entries_declaration(entries)
    lqn_text += activities_declaration(tasks, num_activities)
    print(lqn_text)
    save_file(filename, lqn_text)

# Generate and print a random LQN model
max_tasks = 10
num_tasks = random.randint(3, max_tasks)
max_entries_per_task = 1
max_activities_per_entry = 3


generate_random_lqn('lqn1.lqn', num_tasks, max_entries_per_task)