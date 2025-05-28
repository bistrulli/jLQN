from utils import *
from jinja2 import Environment, FileSystemLoader
import random
import os
import re

def get_call_type(task, async_tasks):
    return "z" if task in async_tasks else "y"

def is_parallel_type(task, parallel_tasks):
    return True if task in parallel_tasks else False

def generate_random_lqn(folder_path, lqn_id, num_tasks, call_avg, call_var, p_edge, p_async, p_parallelism):

    # Set up Jinja2 environment
    env = Environment(loader=FileSystemLoader(searchpath=os.path.dirname(os.path.abspath(__file__))))

    # Load the template
    template = env.get_template('lqn_template.j2')

    filename = f"{lqn_id}-{num_tasks}f"

    dag = generate_random_dag_with_one_root(num_tasks, p_edge)
    
    tasks = list(range(num_tasks))

    eligible_async_tasks = [task for task in tasks if task not in dag[0]]
    # The list of tasks that receive calls that are only asynchronous
    async_tasks = random.sample(eligible_async_tasks, int(p_async * len(eligible_async_tasks)))

    eligible_parallel_tasks = [task for task in tasks]
    
    #list(range(1, num_tasks))
    parallel_tasks = random.sample(eligible_parallel_tasks, int(p_parallelism * len(eligible_parallel_tasks)))

    

    # Define the context
    context = {
        'lqn_id': filename,
        'processors': [f"Proc{i}" for i in range(num_tasks)],
        'num_tasks': num_tasks,
        'tasks': [f"Task{i}" for i in range(num_tasks)],
        'dag': dag,
        'call_avg': call_avg,
        'call_var': call_var,
        'async_tasks': async_tasks,
        'get_call_type': get_call_type,
        'parallel_tasks': parallel_tasks,
        'is_parallel_type': is_parallel_type,
        'random_service_time': random_service_time,
        'get_call_number': get_call_number,
        'get_probability': get_probability
    }

    # Render the template with the context
    lqn_text = template.render(context)

    save_file(folder_path, filename, lqn_text)
    print(f"LQN \"{filename}\" generated.")

    return lqn_text

def get_highest_lqn_value(folder_path):
    """
    Given a folder, looks for all the files whose name is in the format "lqnxx-yyf.lqn"
    and gets the xx value of the highest one.

    :param folder_path: The path to the folder to search in.
    :return: The highest xx value found.
    """
    highest_value = 0
    pattern = re.compile(r'lqn(\d+)-\d+f\.lqn')

    for filename in os.listdir(folder_path):
        match = pattern.match(filename)
        if match:
            value = int(match.group(1))
            if value > highest_value:
                highest_value = value

    return highest_value

if __name__ == '__main__':
    args = get_cli()

    max_len = 2 #len(str(args.num_lqns))

    today_folder_path = create_today_folder()

    starting_id = int(get_highest_lqn_value(today_folder_path)) + 1

    for i in range(starting_id, starting_id + args.num_lqns):
       padded_id = str(i).zfill(max_len)
       num_functions = random.randint(args.min_functions, args.max_functions)
       lqn_text = generate_random_lqn(today_folder_path, f'lqn{padded_id}', num_functions, args.call_avg, args.call_var,
                                      args.prob_edge, args.prob_async, args.prob_parallel)
        #print(lqn_text)