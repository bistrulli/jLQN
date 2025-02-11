from utils import *

def header_declaration(lqn_id):
    """Generate the header section in the standard lqn file format.

    :param lqn_id:  The id of the lqn.
    :return:        The string of the header section of the lqn.
    """
    text = "# Generated with string concatenation\n"
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
    """Generate the activities section in the standard lqn file format.

    :param tasks:       A list containing all task names.
    :param dag:         A directed acyclic graph representing task dependencies.
    :param call_avg:    The average number of calls.
    :param call_var:    The variance of the number of calls.
    :return:            The string of the section of the lqn relative to activities.
    """
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
                text += f"  acti{i} -> ({get_probability(0, len(dag[i]))})acti{i}{dag[i][0]}"
                text += "".join([f" + ({get_probability(j, len(dag[i]))})acti{i}{dag[i][j]}" for j in range(1, len(dag[i]))])
                if i != 0: # Reference task does not issue replies
                    text += "".join([f";\n  acti{i}{dag[i][j]}[Entr{i}]" for j in range(len(dag[i]))])
                text += "\n"
        text += "-1\n\n"
    return text

def generate_random_lqn(lqn_id, num_tasks, call_avg, call_var, prob_edge):

    filename = f"{num_tasks}fun-{lqn_id}-concatenation"

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

    save_file(out_lqn_folder, filename, lqn_text)
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