import random
import os
import argparse
import numpy as np

out_lqn_folder = os.path.join(os.getcwd(), "LQNs")

def get_cli():
    """
    Get input arguments from CLI.
    :return:    ArgumentParser object.
    """
    parser = argparse.ArgumentParser(description="Generate a set of random LQNs.")
    
    # Number of LQNs
    parser.add_argument("-n", "--number", type=int, default=10,
                        help='The number of LQNs to generate (default is 10).', required=False)
    
    # Density of the DAG
    parser.add_argument("-f", "--functions", type=int,
                        help='The number of functions for each LQN.', required=True)
    parser.add_argument("-p", "--prob_edge", type=float,
                        help='The probability of an edge to be formed.', required=True)
    
    # Gaussian distributed calls
    parser.add_argument("-a", "--call_avg", type=int, default=1,
                        help='The average number of calls for each task.', required=False)
    parser.add_argument("-v", "--call_var", type=float, default=0.001,
                        help='The variance of calls for each task.', required=False)
    
    return parser.parse_args()

def save_file(filename, text_string):
    """Save a randomly generated LQN in a .lqn file inside the out_lqn_folder.

    :param filename:    The filename used for the lqn file (excluding extension).
    :param text_string: The lqn text to save in the file.
    """
    with open(f"{out_lqn_folder}/{filename}.lqn", "w") as file:
        file.write(text_string)

def random_service_time(lb=0.1, ub=4.0):
    """Randomly pick a uniformely distributed number for the service time.

    :param lb:  The lower bound of the uniform distribution.
    :param ub:  The upper bound of the uniform distribution.
    :return:    The random service time (e.g. 2.32).
    """
    return round(random.uniform(lb, ub), 2)

def get_call_number(average, variance):
    """Randomly pick a non-negative integer number for a call in a Gaussian distribution.

    :param average:     The average of the Gaussian distribution.
    :param variance:    The variance of the Gaussian distribution.
    :return:            A feasible number of calls.
    """
    std = np.sqrt(variance)
    sample = round(np.random.normal(loc=average, scale=std))
    return 1.0 if sample <= 0 else sample

def generate_random_dag_with_one_root(num_vertices, prob_edge):
    """Generate a random DAG with a single root.

    :param num_vertices:    The number of vertices in the DAG.
    :param prob_edge:       The probability of an edge to be formed.
    :return:                An adjacency list describing the randomly formed DAG.     
    """
    adj_list = [list() for _ in range(num_vertices)]

    # Create edges ensuring no cycles are formed
    for i in range(0, num_vertices): # for i in range(1, num_vertices):
        for j in range(i + 1, num_vertices):
            if random.random() < prob_edge:
                adj_list[i].append(j)

        # Connect the current vertex to the root with some probability
        # if random.random() < prob_root :
        #    adj_list[0].append(i)

    # Add vertices that have no parent to the root
    for i in range(1, num_vertices):
        if not any(i in sublist for sublist in adj_list):
            adj_list[0].append(i)

    # Order list of the root
    adj_list[0].sort()

    return adj_list

def header_declaration(lqn_id):
    """Generate the header section in the standard lqn file format.

    :param lqn_id:  The id of the lqn.
    :return:        The string of the header section of the lqn.
    """
    text = ""
    text += "G\n"
    text += f"\"{lqn_id}.lqn\"\n"
    text += "0.01\n"
    text += "10000\n"
    text += "1\n"
    text += "0.5\n"
    text += "-1\n\n"
    return text

def processors_declaration(processors):
    """Generate the processors section in the standard lqn file format.

    :param processors:  A list containing all processor names.
    :return:            The string of the section of the lqn relative to processors.
    """
    text = ""
    text += "# Processors declaration, with multiplicity\n"
    text += "P 0\n"
    for processor in processors:
        text += f"p {processor} f m 1000\n"
    text += "-1\n\n"
    return text

def tasks_declaration(tasks):
    """Generate the tasks section in the standard lqn file format.

    :param tasks:   A list containing all task names.
    :return:        The string of the section of the lqn relative to tasks.
    """
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
    """Generate the entries section in the standard lqn file format.

    :param entries: A list containing all entry names.
    :return:        The string of the section of the lqn relative to entries.
    """
    text = ""
    text += "# Entries declaration\n"
    text += "E 0\n"
    for index, entry in enumerate(entries):
        text += f"A {entry} acti{index}\n"
    text += "-1\n\n"
    return text



def activities_declaration(tasks, dag, call_avg, call_var):
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
            text += "".join([f"y acti{i}{dag[i][j]} Entr{dag[i][j]} {get_call_number(call_avg, call_var)}\n" for j in range(len(dag[i]))])
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

def generate_random_lqn(lqn_id, num_tasks, call_avg, call_var, prob_edge):

    filename = f"{num_tasks}fun-{lqn_id}"

    lqn_text = header_declaration(filename)

    processors = [f"Proc{i}" for i in range(num_tasks)]
    lqn_text += processors_declaration(processors)

    tasks = [f"Task{i}" for i in range(num_tasks)]
    lqn_text += tasks_declaration(tasks)

    # One entry per task
    entries = [f"Entr{i}" for i in range(num_tasks)]
    lqn_text += entries_declaration(entries)
    
    dag = generate_random_dag_with_one_root(num_tasks, prob_edge)
    lqn_text += activities_declaration(tasks, dag, call_avg, call_var)

    save_file(filename, lqn_text)
    print(f"LQN \"{filename}\" generated.")

    return lqn_text



if __name__ == '__main__':
    args = get_cli()

    max_len = len(str(args.number))

    for i in range(0, args.number):
        padded_id = str(i).zfill(max_len)
        lqn_text = generate_random_lqn(f'lqn{padded_id}', args.functions, args.call_avg, args.call_var, args.prob_edge)
        # print(lqn_text)


# TODO
# num chiamate medie per connessione (distribuz gaussiana)
# flag (chiamate asincrone?) con relativa probabilità