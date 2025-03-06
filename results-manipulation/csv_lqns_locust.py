import os

def print_lqn_files(lqnname):
    out_file_path = f'resources/wasteless_journal/out/{lqnname}.out'
    csv_file_path = f'output/{lqnname}.lqn/Entr0/validation_stats.csv'
    
    try:
        with open(out_file_path, 'r') as out_file:
            print(f"Contents of {out_file_path}:")
            print(out_file.read())
    except FileNotFoundError:
        print(f"File {out_file_path} not found.")
    
    try:
        with open(csv_file_path, 'r') as csv_file:
            print(f"\nContents of {csv_file_path}:")
            print(csv_file.read())
    except FileNotFoundError:
        print(f"File {csv_file_path} not found.")

# Example usage
# print_lqn_files('example_lqn_name')