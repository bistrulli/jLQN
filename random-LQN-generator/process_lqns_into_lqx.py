import subprocess
import os
import shutil


def turn_lqn_into_deterministic_lqx(filepath):
    # Run the lqns command to generate the .lqxo file
    lqns_command = f"lqns -x {filepath}"
    subprocess.run(lqns_command, shell=True, check=True)

    # Construct the paths to the resulting files
    lqxo_filepath = filepath.replace('.lqn', '.lqxo')
    out_filepath = filepath.replace('.lqn', '.out')

    # Check if the .lqxo file exists
    if not os.path.exists(lqxo_filepath):
        print(f"Error: The file {lqxo_filepath} does not exist.")
        return

    # Rename the .lqxo file to .lqnx
    lqnx_filepath = filepath.replace('.lqn', '.lqx')
    shutil.copy(lqxo_filepath, lqnx_filepath)


    with open(lqnx_filepath, 'w') as outfile:
        with open(lqxo_filepath, 'r') as infile:
            lines = infile.readlines()
            for line in lines:
                if 'host-demand-mean="' in line:
                    # Add ''host-demand-cvsq="0.0"'' to the line
                    # Add 'call-order="DETERMINISTIC"' to the line
                    parts = line.split('host-demand-mean="')
                    before = parts[0]
                    after = parts[1]
                    value = after.split('"')[0]
                    new_line = f'{before}host-demand-mean="{value}" host-demand-cvsq="0.0" call-order="DETERMINISTIC" {after[len(value)+1:]}'
                    outfile.write(new_line)
                else:
                    outfile.write(line)


    # Read the .lqnx file and remove lines that start with "<result"
    # with open(lqnx_filepath, 'w') as outfile:
    #     with open(lqxo_filepath, 'r') as infile:
    #         lines = infile.readlines()
    #         skip = False
    #         for line in lines:
    #             if '<solver-params' in line:
    #                 skip = True
    #             if not skip and not line.strip().startswith('<result'):
    #                 if 'host-demand-mean="' in line:
    #                     parts = line.split('host-demand-mean="')
    #                     before = parts[0]
    #                     after = parts[1]
    #                     value = after.split('"')[0]
    #                     new_line = f'{before}host-demand-mean="{value}" host-demand-cvsq="0.0"{after[len(value)+1:]}'
    #                     outfile.write(new_line)
    #                 else:
    #                     outfile.write(line)
    #             if '</solver-params>' in line:
    #                 skip = False

    # Remove the output files of the first lqns execution
    os.remove(lqxo_filepath)
    os.remove(out_filepath)            

    # Run the lqns command again to generate the new deterministic output files
    lqns_command = f"lqns {lqnx_filepath} --exact-mva"
    subprocess.run(lqns_command, shell=True, check=True)
                



# Process all .lqn files in the LQNs folder
lqns_folder = './LQNs/20250320/'
print(f"Processing all .lqn files in {lqns_folder}")
for filename in os.listdir(lqns_folder):
    if filename.endswith('.lqn'):
        print(f"Processing {filename}")
        filepath = os.path.join(lqns_folder, filename)
        turn_lqn_into_deterministic_lqx(filepath)