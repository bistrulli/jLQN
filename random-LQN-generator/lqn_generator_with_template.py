from utils import *
from jinja2 import Environment, FileSystemLoader

def generate_random_lqn(lqn_id, num_tasks, call_avg, call_var, prob_edge):
    # Set up Jinja2 environment
    env = Environment(loader=FileSystemLoader(searchpath='/home/robb/git/jLQN/random-LQN-generator'))

    # Load the template
    template = env.get_template('lqn_template.j2')

    filename = f"{num_tasks}fun-{lqn_id}"

    # Define the context
    context = {
        'lqn_id': filename,
        'processors': [f"Proc{i}" for i in range(num_tasks)],
        'num_tasks': num_tasks,
    }

    # Render the template with the context
    output = template.render(context)

    return output



if __name__ == '__main__':
    args = get_cli()

    max_len = len(str(args.number))

    for i in range(0, args.number):
        padded_id = str(i).zfill(max_len)
        lqn_text = generate_random_lqn(f'lqn{padded_id}', args.functions, args.call_avg, args.call_var, args.prob_edge)
        print(lqn_text)