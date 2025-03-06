from utils import *
from jinja2 import Environment, FileSystemLoader
import random

def get_call_type(p_async):
    return "z" if random.random() < p_async else "y"

def generate_random_lqn(lqn_id, num_tasks, call_avg, call_var, p_edge, p_async):
    # Set up Jinja2 environment
    env = Environment(loader=FileSystemLoader(searchpath='/home/robb/git/jLQN/random-LQN-generator'))

    # Load the template
    template = env.get_template('lqn_template.j2')

    filename = f"{lqn_id}-{num_tasks}functions"

    dag = generate_random_dag_with_one_root(num_tasks, p_edge)

    # Define the context
    context = {
        'lqn_id': filename,
        'processors': [f"Proc{i}" for i in range(num_tasks)],
        'num_tasks': num_tasks,
        'tasks': [f"Task{i}" for i in range(num_tasks)],
        'dag': dag,
        'call_avg': call_avg,
        'call_var': call_var,
        'p_async': p_async,
        'get_call_type': get_call_type,
        'random_service_time': random_service_time,
        'get_call_number': get_call_number,
        'get_probability': get_probability
    }

    # Render the template with the context
    lqn_text = template.render(context)

    save_file(out_lqn_folder, filename, lqn_text)
    print(f"LQN \"{filename}\" generated.")

    return lqn_text



if __name__ == '__main__':
    args = get_cli()

    max_len = len(str(args.number))

    for i in range(0, args.number):
        padded_id = str(i).zfill(max_len)
        num_functions = random.randint(args.min_functions, args.max_functions)
        lqn_text = generate_random_lqn(f'lqn{padded_id}', num_functions, args.call_avg, args.call_var, args.p_edge, args.p_async)
        #print(lqn_text)