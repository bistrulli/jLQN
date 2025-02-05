import random
import os
import argparse

out_lqn_folder = os.path.join(os.getcwd(), "LQNs")


def get_cli():
    """
    Get input arguments from CLI.
    :return:    ArgumentParser object.
    """
    parser = argparse.ArgumentParser(description="Generate a set of random LQNs.")
    parser.add_argument("-n", "--number", type=int, default=10,
                        help='The number of LQNs to generate (default is 10).', required=False)
    parser.add_argument("-f", "--functions", type=int,
                        help='The number of functions for each LQN.', required=True)
    return parser.parse_args()



def random_service_time():
    return round(random.uniform(0.1, 4.0), 2)

def save_file(filename, text_string):
    with open(f"{out_lqn_folder}/{filename}", "w") as file:
        file.write(text_string)

def generate_random_dag_with_one_root(num_vertices, prob_edge=0.5, prob_root=0.3):
    adj_list = [list() for _ in range(num_vertices)]

    # Create edges ensuring no cycles are formed
    for i in range(1, num_vertices):
        for j in range(i + 1, num_vertices):
            if random.random() < prob_edge:
                adj_list[i].append(j)

        # Connect the current vertex to the root with some probability
        if random.random() < prob_root :
            adj_list[0].append(i)

    # Add vertices that have no parent to the root
    for i in range(1, num_vertices):
        if not any(i in sublist for sublist in adj_list):
            adj_list[0].append(i)

    # Order list of the root
    adj_list[0].sort()

    return adj_list

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

def get_call_number():
    return 1.0


def activities_declaration(tasks, dag):
    text = ""
    text += "# Activities declaration\n"
    for i, task in enumerate(tasks):
        text += f"A {task}\n"
        if len(dag[i]) == 0: # Vertex is a leaf
            text += f"s acti{i} {random_service_time()}\n"
            text += ":\n"
            text += f"  acti{i}[Entr{i}]\n"
        else:
            text += f"s acti{i} 0.0001\n"
            text += "".join([f"s acti{i}{dag[i][j]} {random_service_time()}\n" for j in range(len(dag[i]))])
            text += "".join([f"y acti{i}{dag[i][j]} Entr{dag[i][j]} {get_call_number()}\n" for j in range(len(dag[i]))])
            text += ":\n"
            if len(dag[i]) == 1: # Vertex has one connection
                text += f"  acti{i} -> acti{i}{dag[i][0]};\n"
                text += f"  acti{i}{dag[i][0]}[Entr{i}]\n"
            else: # Vertex has 2 or more connections
                # Almost equal probability for choices
                p = round(1 / len(dag[i]), 2)
                p0 = round(1 - ((len(dag[i])-1) * p), 2)
                text += f"  acti{i} -> ({p0})acti{i}{dag[i][0]}"
                text += "".join([f" + ({p})acti{i}{dag[i][j]}" for j in range(1, len(dag[i]))])
                if i != 0: # Reference task does not issue replies
                    text += "".join([f";\n  acti{i}{dag[i][j]}[Entr{i}]" for j in range(len(dag[i]))])
                text += "\n"
        text += "-1\n\n"
    return text

def generate_random_lqn(filename, num_tasks, max_entries_per_task=1, max_activities_per_entry=3):
    lqn_text = header_declaration(filename)

    processors = [f"Proc{i}" for i in range(num_tasks)]
    lqn_text += processors_declaration(processors)

    tasks = [f"Task{i}" for i in range(num_tasks)]
    lqn_text += tasks_declaration(tasks)

    # One entry per task
    entries = [f"Entr{i}" for i in range(num_tasks)]
    lqn_text += entries_declaration(entries)
    
    dag = generate_random_dag_with_one_root(num_tasks, 0.5, 0.3)
    lqn_text += activities_declaration(tasks, dag)

    save_file(filename, lqn_text)

    return lqn_text



if __name__ == '__main__':
    args = get_cli()
    for i in range(0, args.number-1):
        lqn_text = generate_random_lqn(f'lqn{i}.lqn', args.functions)
        # print(lqn_text)


# TODO
# num chiamate medie per connessione (distribuz gaussiana)
# flag (chiamate asincrone?) con relativa probabilità