import random
import numpy as np
import argparse
import os
from datetime import datetime

out_lqn_folder = os.path.join(os.getcwd(), "LQNs")

def get_cli():
    """
    Get input arguments from CLI.
    :return:    ArgumentParser object.
    """
    parser = argparse.ArgumentParser(description="Generate a set of random LQNs.")
    
    # Number of LQNs
    parser.add_argument("-n", "--num-lqns", type=int, default=1,
                        help='The number of LQNs to generate (default is 1).', required=False)
    
    # Density of the DAG
    parser.add_argument("-max", "--max-functions", type=int,
                        help='The maximum number of functions for each LQN.', required=True)
    parser.add_argument("-min", "--min-functions", type=int,
                        help='The minimum number of functions for each LQN.', required=True)
    parser.add_argument("-p", "--prob-edge", type=float,
                        help='The probability of an edge to be formed.', required=True)
    
    # Gaussian distributed calls
    parser.add_argument("-a", "--call-avg", type=int, default=1,
                        help='The average number of calls for each task (default is 1).', required=False)
    parser.add_argument("-v", "--call-var", type=float, default=0,
                        help='The variance of calls for each task (default is 0).', required=False)

    # Asynchronous calls and parallelism
    parser.add_argument("-async", "--prob-async", type=float, default=0.1,
                        help='The probability of an asynchronous call.', required=False)
    parser.add_argument("-parallel", "--prob-parallel", type=float, default=0.0,
                        help='The probability of parallel calls.', required=False)
    
    return parser.parse_args()

def save_file(output_folder, filename, text_string):
    """Save a randomly generated LQN in a .lqn file inside the out_lqn_folder.

    :param filename:    The filename used for the lqn file (excluding extension).
    :param text_string: The lqn text to save in the file.
    """
    with open(f"{output_folder}/{filename}.lqn", "w") as file:
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

def generate_random_dag_with_one_root(num_vertices, p_edge):
    """Generate a random DAG with a single root.

    :param num_vertices:    The number of vertices in the DAG.
    :param prob_edge:       The probability of an edge to be formed.
    :return:                An adjacency list describing the randomly formed DAG.     
    """
    adj_list = [list() for _ in range(num_vertices)]

    # Create edges ensuring no cycles are formed
    for i in range(0, num_vertices): # for i in range(1, num_vertices):
        for j in range(i + 1, num_vertices):
            if random.random() < p_edge:
                adj_list[i].append(j)

    # Add vertices that have no parent to the root
    for i in range(1, num_vertices):
        if not any(i in sublist for sublist in adj_list):
            adj_list[0].append(i)

    # Order list of the root
    adj_list[0].sort()

    return adj_list

def get_probability(node_position, num_edges):
    """Calculate the (almost) equal probability of the edge connecting the current node with node_id in the DAG.
    The sum of the probabilities is 1.

    :param node_position:   The position of the node in the list.
    :param num_edges:       The number of edges from the current node.
    :return:                The probability of the edge.
    """
    p = round(1 / num_edges, 2)
    if node_position != 0:
        return p
    else:
        return round(1 - ((num_edges-1) * p), 2)
    
def create_today_folder():
    # Get today's date in YYYYMMDD format
    today = datetime.today().strftime('%Y%m%d')
    
    # Create the full path for the new folder
    folder_path = os.path.join(out_lqn_folder, today)
    
    # Create the folder if it doesn't exist
    if not os.path.exists(folder_path):
        os.makedirs(folder_path)
        print(f"Folder '{folder_path}' created.")
    else:
        print(f"Folder '{folder_path}' already exists.")
    return folder_path