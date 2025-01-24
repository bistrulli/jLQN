import random
import os

out_lqn_folder = os.path.join(os.getcwd(), "LQNs")

def header_declaration(filename):
    header = ""
    header += "G\n"
    header += f"{filename}\n"
    header += "0.01\n"
    header += "10000\n"
    header += "1\n"
    header += "0.5\n"
    header += "-1\n\n"
    return header


def processors_declaration(num_processors):
    # TODO change multiplicity
    processors_string = ""
    processors_string += "# Processors declaration, with multiplicity\n"
    processors_string += "P 0\n"
    for i in range(num_processors):
        processors_string += f"p Proc{i} f m 1000\n"
    processors_string += "-1\n\n"
    return processors_string


def tasks_and_entries_declaration(num_tasks, max_entries_per_task):
  #TODO change moltiplicity
  tasks_string = ""
  tasks_string += "# Tasks declaration\n"
  tasks_string += "T 0\n"

  entries_string = ""
  entries_string += "# Entries declaration\n"
  entries_string += "E 0\n"

  for i in range(num_tasks):
      task_name = f"Task{i}"
      a = "r" if i == 0 else "n"
      num_entries = random.randint(1, max_entries_per_task)
      entry_names = [f"Entry{i}_{j}" for j in range(num_entries)]
      tasks_string += f"t {task_name} {a} "
      tasks_string += " ".join(entry_names) + f" -1 Proc{i} m 1000\n"
      for entry_name in entry_names:
        entries_string += f"s {entry_name} {round(random.uniform(0.001, 0.01), 4)} -1\n"

  tasks_string += "-1\n\n"
  entries_string += "-1\n\n"

  return tasks_string + entries_string


def save_file(filename, text_string):
    with open(f"{out_lqn_folder}/{filename}", "w") as file:
        file.write(text_string)



def generate_random_lqn(filename, num_tasks=5, max_entries_per_task=3):
    lqn_text = header_declaration(filename)
    lqn_text += processors_declaration(num_tasks)
    lqn_text += tasks_and_entries_declaration(num_tasks, max_entries_per_task)
    print(lqn_text)
    save_file(filename, lqn_text)

    # # Activities (Simplified)
    # for i in range(num_tasks):
    #     task_name = f"Task{i}"
    #     lqn_model += f"# Activities definition\n"
    #     lqn_model += f"A {task_name}\n"
    #     for j in range(num_entries_per_task):
    #         activity_name = f"Activity{i}_{j}"
    #         lqn_model += f"  s {activity_name} 0.01\n" 
    #     # Simplified activity dependencies and routing
    #     lqn_model += "  y Activity0_0 Entry0_0 1.0\n" 
    #     lqn_model += "  y Activity0_1 Entry0_1 1.0\n" 
    #     lqn_model += "  y Activity0_2 Entry0_2 1.0\n" 
    #     lqn_model += f":\n" 
    #     lqn_model += f"  Activity0_0 -> (0.33)Activity0_1 + (0.33)Activity0_2 + (0.34)Activity0_0;\n" 
    #     lqn_model += f"  Activity0_1[Entry0_0];\n" 
    #     lqn_model += f"  Activity0_2[Entry0_0];\n" 
    #     lqn_model += f"  Activity0_0[Entry0_0]\n" 
    #     lqn_model += "-1\n\n"

    # return lqn_model

# Generate and print a random LQN model
max_tasks = 10
num_tasks = random.randint(1, max_tasks)

max_entries_per_task = 5

generate_random_lqn('lqn1.lqn', num_tasks, max_entries_per_task)