import re
import os
import tempfile
import subprocess
import argparse
import xml.etree.ElementTree as ET  # For XML parsing
import csv  # For CSV output
import itertools  # Per generare combinazioni di parametri
import datetime  # Per timestamp
import time  # Per misurare il tempo di esecuzione
import numpy as np
from scipy import io  # For MATLAB (.mat) file output

def save_results_to_matlab(parsed_results_list, parameter_values_list, output_path, output_dir=None):
    """
    Save results from multiple LQN simulations to a single MATLAB (.mat) file.
    
    Args:
        parsed_results_list (list): List of dictionaries with parsed results from each simulation
        parameter_values_list (list): List of dictionaries with parameter values used in each simulation
        output_path (str): Base path for the output file
        output_dir (str, optional): Directory where to save the MAT file
    
    Returns:
        str: Path to the created MAT file or None if an error occurred
    """
    if not parsed_results_list:
        print("No results to save to MATLAB file")
        return None
    
    try:
        # Create a base name for the MAT file
        base_path = os.path.splitext(output_path)[0]
        timestamp = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
        
        # If output_dir is specified, use that directory
        if output_dir:
            base_filename = os.path.basename(base_path)
            mat_path = os.path.join(output_dir, f"{base_filename}_results_{timestamp}.mat")
        else:
            mat_path = f"{base_path}_results_{timestamp}.mat"
        
        # Get all unique entry IDs and processor IDs across all results
        all_entries = set()
        all_processors = set()
        
        for parsed_results in parsed_results_list:
            all_entries.update(parsed_results['entry_response_time'].keys())
            all_processors.update(parsed_results['processor_utilization'].keys())
        
        # Sort the IDs for consistent ordering
        entry_ids = sorted(list(all_entries))
        processor_ids = sorted(list(all_processors))
        
        # Get all N_USERS values as Cli vector
        cli = np.array([param_values.get('N_USERS', 0) for param_values in parameter_values_list])
        
        # Prepare matrices
        n_experiments = len(parsed_results_list)
        n_entries = len(entry_ids)
        
        # Response time matrix (RTm) - rows: experiments, columns: service stations (entries)
        rtm = np.zeros((n_experiments, n_entries))
        
        # Throughput matrix (Tm) - same dimensions as RTm
        tm = np.zeros((n_experiments, n_entries))
        
        # Capacity constraints matrix (NC) - rows: experiments, columns: processors
        nc = np.zeros((n_experiments, len(processor_ids)))
        
        # Fill the matrices
        for i, (parsed_results, param_values) in enumerate(zip(parsed_results_list, parameter_values_list)):
            # Fill RTm (response times)
            for j, entry_id in enumerate(entry_ids):
                rtm[i, j] = parsed_results['entry_response_time'].get(entry_id, 0)
            
            # Fill Tm (throughputs)
            for j, entry_id in enumerate(entry_ids):
                tm[i, j] = parsed_results['entry_throughput'].get(entry_id, 0)
            
            # Fill NC (capacity constraints) from N_CORES_x parameters
            for j, proc_id in enumerate(processor_ids):
                # Extract processor number from proc_id (assuming format like "Proc1")
                try:
                    proc_num = proc_id.replace("Proc", "")
                    core_param = f'N_CORES_{proc_num}'
                    nc[i, j] = param_values.get(core_param, 0)
                except Exception:
                    nc[i, j] = 0
        
        # Create dictionary with all matrices to save in MATLAB format
        matlab_data = {
            'RTm': rtm,  # Response time matrix
            'Tm': tm,    # Throughput matrix
            'Cli': cli,  # Client vector
            'NC': nc     # Capacity constraints matrix
        }
        
        # Add metadata
        matlab_data['entry_ids'] = np.array([str(entry_id) for entry_id in entry_ids], dtype=np.object_)
        matlab_data['processor_ids'] = np.array([str(proc_id) for proc_id in processor_ids], dtype=np.object_)
        
        # Save to MATLAB file
        io.savemat(mat_path, matlab_data)
        
        print(f"Results saved in MATLAB format: {mat_path}")
        return mat_path
    
    except Exception as e:
        print(f"Error during saving results to MATLAB file: {e}")
        return None

def save_results_to_csv(parsed_results, output_path, parameter_values, output_dir=None):
    """
    Salva i risultati parsati dal file XML in un unico file CSV.
    
    Args:
        parsed_results (dict): Dizionario con i risultati parsati (processor_utilization, entry_response_time, entry_throughput)
        output_path (str): Percorso del file di output XML da cui deriva il nome del file CSV
        parameter_values (dict): Dizionario con i valori dei parametri usati nella simulazione
        output_dir (str, optional): Directory specifica dove salvare il file CSV, se diversa dalla directory di output_path
    
    Returns:
        str: Percorso del file CSV creato o None se si è verificato un errore
    """
    if not parsed_results:
        print("Nessun risultato da salvare nel CSV")
        return None
    
    try:
        # Crea un nome per il file CSV basato sul file di output XML
        csv_base_path = os.path.splitext(output_path)[0]
        
        # Estrai informazioni sulla configurazione per includerle nel nome del file
        config_str = ""
        
        # Includi N_USERS se presente
        if 'N_USERS' in parameter_values:
            config_str += f"_users{parameter_values['N_USERS']}"
        
        # Cerca e includi tutti i parametri N_CORES_*
        cores_params = sorted([p for p in parameter_values if p.startswith('N_CORES_')])
        if cores_params:
            config_str += "_cores"
            for param in cores_params:
                # Estrai il numero dal nome del parametro (es. da N_CORES_1 estrai 1)
                core_num = param.split('_')[-1]
                config_str += f"-{core_num}-{parameter_values[param]}"
        
        # Aggiungi timestamp se config_str è vuoto (nessun parametro trovato)
        if not config_str:
            import datetime
            timestamp = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
            config_str = f"_config{timestamp}"
        
        # Se output_dir è specificato, usa quella directory invece di quella di output_path
        if output_dir:
            # Ottieni solo il nome base del file senza percorso
            base_filename = os.path.basename(csv_base_path)
            csv_path = os.path.join(output_dir, f"{base_filename}{config_str}_results.csv")
        else:
            csv_path = f"{csv_base_path}{config_str}_results.csv"
        
        # Crea un unico file CSV nel formato "long" con colonne: metric, value, id, type
        with open(csv_path, 'w', newline='') as csvfile:
            writer = csv.writer(csvfile)
            # Intestazione
            writer.writerow(['metric', 'value', 'id', 'type'])
            
            # Aggiungi i parametri di configurazione come righe aggiuntive
            for param_name, param_value in parameter_values.items():
                if param_name.startswith('N_'):  # includi solo parametri di modello
                    if param_name == 'N_USERS':
                        writer.writerow(['configuration', param_value, param_name, 'users'])
                    elif param_name.startswith('N_CORES_'):
                        core_num = param_name.split('_')[-1]
                        writer.writerow(['configuration', param_value, param_name, f'processor_cores-{core_num}'])
            
            # Utilizzo processori
            for proc_id, util_value in sorted(parsed_results['processor_utilization'].items()):
                writer.writerow(['utilization', util_value, proc_id, 'processor'])
            
            # Tempi di risposta entry
            for entry_id, resp_time in sorted(parsed_results['entry_response_time'].items()):
                writer.writerow(['response_time', resp_time, entry_id, 'entry'])
            
            # Throughput entry
            for entry_id, throughput in sorted(parsed_results['entry_throughput'].items()):
                writer.writerow(['throughput', throughput, entry_id, 'entry'])
        
        print(f"Risultati salvati in formato CSV: {csv_path}")
        return csv_path
    
    except Exception as e:
        print(f"Errore durante il salvataggio dei risultati in CSV: {e}")
        return None

def parse_lqns_xml_results(xml_file_path):
    print(f"Parsing XML results from: {xml_file_path}")
    """
    Parse the XML output file generated by lqns with the -x option.
    
    Args:
        xml_file_path (str): Path to the XML output file.
        
    Returns:
        dict: A dictionary containing processor utilizations, entry response times, and entry throughputs.
              Format:
              {
                 'processor_utilization': {
                     'Proc0': 0.5,
                     'Proc1': 0.75,
                     ...
                 },
                 'entry_response_time': {
                     'Entr0': 0.25,
                     'Entr1': 1.5,
                     ...
                 },
                 'entry_throughput': {
                     'Entr0': 10.0,
                     'Entr1': 5.0,
                     ...
                 }
              }
        None: If the XML file cannot be parsed.
    """
    try:
        if not os.path.exists(xml_file_path):
            print(f"Error: XML output file not found at {xml_file_path}")
            return None
            
        # Parse the XML file
        tree = ET.parse(xml_file_path)
        root = tree.getroot()
        
        results = {
            'processor_utilization': {},
            'entry_response_time': {},
            'entry_throughput': {}
        }
        
        # Find processor utilizations
        # Assuming XML structure like: <processor name="Proc0"><result-processor utilization="0.75"/></processor>
        for processor in root.findall('.//processor'):
            proc_name = processor.get('name')
            if proc_name:
                util_elem = processor.find('./result-processor')
                if util_elem is not None and 'utilization' in util_elem.attrib:
                    utilization = float(util_elem.get('utilization'))
                    results['processor_utilization'][proc_name] = utilization
        
        # Find entry response times and throughputs
        # Assuming XML structure like: <entry name="Entr0"><result-entry throughput="10.0" phase1-service-time="0.25"/></entry>
        for entry in root.findall('.//entry'):
            entry_name = entry.get('name')
            if entry_name:
                result_elem = entry.find('./result-entry')
                if result_elem is not None:
                    # Get throughput
                    if 'throughput' in result_elem.attrib:
                        throughput = float(result_elem.get('throughput'))
                        results['entry_throughput'][entry_name] = throughput
                    
                    # Get response time (may be stored differently in different versions of lqns)
                    # Extract only 'phase1-service-time' attribute as requested
                    if 'phase1-service-time' in result_elem.attrib:
                        resp_time = float(result_elem.get('phase1-service-time'))
                        results['entry_response_time'][entry_name] = resp_time
                    else:
                        print(f"Warning: Entry {entry_name} has no 'phase1-service-time' attribute")
        
        # If we didn't find standard structures, try alternative XML structures
        # Check if we found any results. If not, try alternate XML structures
        if not results['processor_utilization'] and not results['entry_response_time'] and not results['entry_throughput']:
            print("Warning: No results found with standard XML structure. No fallback method will be used.")
        
        # Print summary of what we found
        print(f"Found {len(results['processor_utilization'])} processor utilizations")
        print(f"Found {len(results['entry_response_time'])} entry response times")
        print(f"Found {len(results['entry_throughput'])} entry throughputs")
        
        return results
        
    except ET.ParseError as e:
        print(f"XML parsing error: {e}")
        # If XML parsing fails, try checking if the file contains XML at all
        try:
            with open(xml_file_path, 'r') as f:
                content = f.read().strip()
                if content.startswith('<?xml') or content.startswith('<'):
                    print("File contains XML but has syntax errors.")
                else:
                    print("File does not appear to contain XML data.")
        except Exception:
            pass
        return None
    except Exception as e:
        print(f"Error parsing XML results: {e}")
        return None

def _run_lqns_solver(lqn_content, lqns_binary_path, original_file_path):
    """Runs the lqns solver on the given LQN content using a temporary file.
    Saves the output to a file in the same directory as the original LQN file.

    Args:
        lqn_content (str): The LQN model content (with numerical values).
        lqns_binary_path (str): Path to the lqns solver executable.
        original_file_path (str): Path to the original parametric LQN file.

    Returns:
        tuple: (solver_stdout, output_file_path) where:
            - solver_stdout is the standard output from the lqns solver on success, or None on failure.
            - output_file_path is the path where the solver output was saved.
    """
    temp_lqn_path = None
    
    # Create output file path in the same directory as original file
    original_dir = os.path.dirname(original_file_path)
    original_filename = os.path.basename(original_file_path)
    original_name, original_ext = os.path.splitext(original_filename)
    
    # Create output file names
    temp_lqn_filename = f"{original_name}_temp{original_ext}"
    output_filename = f"{original_name}_output.out"
    parseable_output_filename = f"{original_name}.p"
    
    # Full paths for temporary LQN and output file
    if original_dir:
        temp_lqn_path = os.path.join(original_dir, temp_lqn_filename)
        output_file_path = os.path.join(original_dir, output_filename)
        parseable_output_path = os.path.join(original_dir, parseable_output_filename)
    else:
        temp_lqn_path = temp_lqn_filename
        output_file_path = output_filename
        parseable_output_path = parseable_output_filename
    
    try:
        # Write the modified LQN to a file in the same directory
        with open(temp_lqn_path, 'w') as temp_lqn_file:
            temp_lqn_file.write(lqn_content)
        
        print(f"Created temporary file with substituted values: {temp_lqn_path}")
        print(f"Will save solver output to: {output_file_path}")
        print(f"Will save parseable output to: {parseable_output_path}")
        
        # Run the solver and capture stdout, but also redirect to a file
        print(f"Running command: {lqns_binary_path} -x {temp_lqn_path} -o {output_file_path}")
        result = subprocess.run(
            [lqns_binary_path,"--exact-mva", "-x", temp_lqn_path, "-o", output_file_path],
            capture_output=True,
            text=True,
            check=True
        )
        
        print(f"Subprocess completed with return code: {result.returncode}")
        print(f"stdout length: {len(result.stdout)}")
        print(f"stderr length: {len(result.stderr)}")
        
        if result.stderr:
            print("Command stderr:")
            print(result.stderr[:500] + "..." if len(result.stderr) > 500 else result.stderr)
        
        # Check if output file exists and has content
        if os.path.exists(output_file_path):
            file_size = os.path.getsize(output_file_path)
            print(f"Output file size: {file_size} bytes")
            if file_size == 0:
                print("Warning: Output file exists but is empty.")
            else:
                # Read the first few lines to check format
                try:
                    with open(output_file_path, 'r') as f:
                        first_lines = "".join([f.readline() for _ in range(5)])
                        print(f"First few lines of output file:\n{first_lines}")
                        # Check if it contains XML
                        if "<?xml" in first_lines or "<lqn-model" in first_lines:
                            print("Output file contains XML content.")
                        else:
                            print("Output file does not appear to contain XML content.")
                except Exception as e:
                    print(f"Error reading output file: {e}")
        else:
            print(f"Warning: Expected output file {output_file_path} was not created.")

        # The tool might create output with a different filename pattern
        # Try to find any XML files created in the output directory
        output_dir = os.path.dirname(output_file_path) or "."
        xml_files = [f for f in os.listdir(output_dir) if f.endswith('.xml') and 
                    os.path.getmtime(os.path.join(output_dir, f)) > 
                    os.path.getmtime(temp_lqn_path)]
        
        if xml_files:
            print(f"Found {len(xml_files)} XML files created after running lqns: {xml_files}")
            # If output_file_path doesn't exist but we found an XML file, use that
            if not os.path.exists(output_file_path) and xml_files:
                output_file_path = os.path.join(output_dir, xml_files[0])
                print(f"Using alternative XML file: {output_file_path}")
        
        print(f"Solver finished successfully. Output saved to: {output_file_path}")
        
        # Check if parseable output file was created
        if os.path.exists(parseable_output_path):
            print(f"Parseable output saved to: {parseable_output_path}")
        else:
            # The -p option might create output with a different naming convention
            # Try to find a .p file with similar name
            potential_p_files = [f for f in os.listdir(original_dir if original_dir else '.') 
                             if f.startswith(original_name) and f.endswith('.p')]
            if potential_p_files:
                parseable_output_path = os.path.join(original_dir if original_dir else '.', potential_p_files[0])
                print(f"Found parseable output at: {parseable_output_path}")
            else:
                print("Warning: No parseable output file (.p) was found.")
                parseable_output_path = None
        
        # If result.stdout is empty but the output file exists, let's read output file content
        # and use it as the stdout value
        if not result.stdout and os.path.exists(output_file_path) and os.path.getsize(output_file_path) > 0:
            try:
                with open(output_file_path, 'r') as f:
                    result_stdout = f.read()
                    print(f"Read {len(result_stdout)} bytes from output file to use as stdout")
                    # Create a modified result object with the file content as stdout
                    from collections import namedtuple
                    Result = namedtuple('Result', ['stdout', 'stderr', 'returncode'])
                    result = Result(result_stdout, result.stderr, result.returncode)
            except Exception as e:
                print(f"Error reading output file content: {e}")
        
        return result.stdout, output_file_path, parseable_output_path, result.returncode

    except FileNotFoundError:
        print(f"Error: lqns binary not found at '{lqns_binary_path}'. Make sure it's installed and in PATH or provide the full path.")
        return None, None, None, -1
    except subprocess.CalledProcessError as e:
        print(f"Error: lqns solver failed with exit code {e.returncode}")
        print(f"Solver stdout: {e.stdout}")
        print(f"Solver stderr: {e.stderr}")
        return None, None, None, -1
    except Exception as e:
        print(f"An unexpected error occurred during solver execution: {e}")
        return None, None, None, -1
    finally:
        # Clean up temporary file
        if temp_lqn_path and os.path.exists(temp_lqn_path):
            os.remove(temp_lqn_path)
            print(f"Removed temporary file: {temp_lqn_path}")

def solve_parametric_lqn(lqn_file_path, parameter_values, lqns_binary_path="lqns", run_solver=True, output_dir=None, save_csv=True):
    """
    Reads a parametric LQN file, substitutes symbolic parameters with numerical values,
    and calls the lqns solver.

    Args:
        lqn_file_path (str): Path to the input .lqn file.
        parameter_values (dict): A dictionary mapping parameter names (e.g., 'N_USERS', 'N_CORES_1')
                                 to their numerical values.
        lqns_binary_path (str): Path to the lqns solver executable. Defaults to 'lqns'
                                  assuming it's in the system PATH.
        run_solver (bool): Whether to run the solver. Defaults to True.
        output_dir (str, optional): Directory where to save the CSV results, if different from the directory of output_file_path.
        save_csv (bool): Whether to save individual CSV files for each configuration. Defaults to True.

    Returns:
        str: The standard output from the lqns solver if run_solver is True and successful.
             The LQN content with numerical values substituted if run_solver is False.
        None: If the input file doesn't exist or the solver fails.
    """
    if not os.path.exists(lqn_file_path):
        print(f"Error: LQN file not found at {lqn_file_path}")
        return None, None, None, None, -1, None

    try:
        with open(lqn_file_path, 'r') as f:
            lqn_content = f.read()

        modified_lqn_content = lqn_content

        # --- Parameter Identification and Substitution ---
        # Find all potential parameter names (simple approach: words starting with N_)
        # A more robust approach might be needed if parameter names are complex
        potential_params = set(re.findall(r'\b(N_USERS|N_CORES_\d+)\b', lqn_content))

        print(f"Identified potential parameters: {potential_params}")

        missing_params = []
        for param in potential_params:
            print(f"Checking parameter: {param}")
            if param in parameter_values:
                # Substitute the parameter with its numerical value
                # Using word boundaries (\b) to avoid partial matches
                pattern = r"\b" + re.escape(param) + r"\b"
                replacement = str(parameter_values[param])
                modified_lqn_content = re.sub(pattern, replacement, modified_lqn_content)
                print(f"Substituting {param} with {replacement}")
            else:
                missing_params.append(param)

        if missing_params:
            print(f"Warning: Values not provided for parameters: {missing_params}. Keeping them symbolic.")

        if run_solver:
            # Call the separate solver function
            solver_output, output_file_path, parseable_output_path, return_code = _run_lqns_solver(modified_lqn_content, lqns_binary_path, lqn_file_path)
            print(f"Solver return code: {return_code}")
            # Check for success based on return code (0 means success)
            if return_code == 0:
                print(f"Solver results saved to: {output_file_path}")
                if parseable_output_path:
                    print(f"Parseable results saved to: {parseable_output_path}")
                
                # Parse the XML results
                parsed_results = None
                if output_file_path:
                    print(f"Attempting to parse XML results from: {output_file_path}")
                    parsed_results = parse_lqns_xml_results(output_file_path)
                    if parsed_results:
                        print("Successfully parsed XML results.")
                    else:
                        print("Failed to parse XML results.")
                
                # Save results to CSV if we have them and save_csv is True
                csv_path = None
                if parsed_results and save_csv:
                    csv_path = save_results_to_csv(parsed_results, output_file_path, parameter_values, output_dir)
                
                return solver_output, output_file_path, parseable_output_path, parsed_results, return_code, csv_path
            return solver_output, output_file_path, parseable_output_path, None, return_code, None
        else:
            # Return the modified LQN content without running the solver
            return modified_lqn_content, None, None, None, None, None

    except Exception as e:
        print(f"An error occurred during parameter substitution: {e}")
        return None, None, None, None, -1, None

def profile_lqn(lqn_file_path, parameter_ranges, lqns_binary_path="lqns", output_dir=None, save_csv=False):
    """
    Valuta un modello LQN parametrico con diverse combinazioni di parametri.
    
    Args:
        lqn_file_path (str): Percorso al file LQN parametrico
        parameter_ranges (dict): Dizionario dove le chiavi sono i nomi dei parametri
                                e i valori sono liste di valori da testare per quel parametro
        lqns_binary_path (str): Percorso al binario lqns
        output_dir (str): Directory dove salvare tutti i risultati (se None, usa la directory del file LQN)
        save_csv (bool): Se True, salva anche i singoli file CSV per ogni configurazione. Default: False
    
    Returns:
        tuple: (list of csv files, matlab file path)
    """
    if not os.path.exists(lqn_file_path):
        print(f"Error: LQN file not found at {lqn_file_path}")
        return [], None
    
    # Se output_dir non è specificato, usa la directory del file LQN
    if output_dir is None:
        output_dir = os.path.dirname(lqn_file_path)
        if not output_dir:  # Se è una stringa vuota (file nella directory corrente)
            output_dir = '.'
    
    # Crea la directory di output se non esiste
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)
        print(f"Creata directory di output: {output_dir}")
    
    # Crea una sottodirectory chiamata "profile" se non esiste
    profile_dir = os.path.join(output_dir, "profile")
    if not os.path.exists(profile_dir):
        os.makedirs(profile_dir)
        print(f"Creata directory 'profile': {profile_dir}")
    
    # Crea una sottodirectory con timestamp per questa sessione di profiling all'interno di profile
    timestamp = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
    model_name = os.path.splitext(os.path.basename(lqn_file_path))[0]
    session_dir = os.path.join(profile_dir, f"{model_name}_{timestamp}")
    os.makedirs(session_dir)
    print(f"I risultati saranno salvati in: {session_dir}")
    
    # Genera tutte le possibili combinazioni di parametri
    param_names = list(parameter_ranges.keys())
    param_values = list(parameter_ranges.values())
    combinations = list(itertools.product(*param_values))
    
    # Crea un file di log per questa sessione
    log_file_path = os.path.join(session_dir, "profile_log.txt")
    with open(log_file_path, 'w') as log_file:
        log_file.write(f"Profiling di {lqn_file_path} iniziato il {timestamp}\n")
        log_file.write(f"Parametri da variare: {param_names}\n")
        log_file.write(f"Totale combinazioni: {len(combinations)}\n\n")
    
    print(f"Parametri da variare: {param_names}")
    print(f"Totale combinazioni da testare: {len(combinations)}")
    
    # Crea un file CSV di riepilogo
    summary_file_path = os.path.join(session_dir, "profile_summary.csv")
    with open(summary_file_path, 'w', newline='') as summary_file:
        summary_writer = csv.writer(summary_file)
        # Intestazione con tutti i parametri più alcune metriche chiave
        header = param_names + ['max_proc_util', 'bottleneck_proc', 'max_resp_time', 'bottleneck_entry']
        if save_csv:
            header.append('csv_file')
        summary_writer.writerow(header)
    
    # Lista per tenere traccia di tutti i file CSV generati
    csv_files = []
    
    # Liste per raccogliere tutti i risultati per il file MATLAB
    all_parsed_results = []
    all_parameter_values = []
    
    # Esegui ogni combinazione
    start_time = time.time()
    for i, combination in enumerate(combinations):
        # Crea un dizionario di parametri per questa combinazione
        param_dict = {param_names[j]: combination[j] for j in range(len(param_names))}
        
        # Formatta la combinazione per output leggibile
        combo_str = ", ".join([f"{param}={value}" for param, value in param_dict.items()])
        print(f"\nEseguendo combinazione {i+1}/{len(combinations)}: {combo_str}")
        
        # Aggiorna il log
        with open(log_file_path, 'a') as log_file:
            log_file.write(f"Combinazione {i+1}: {combo_str}\n")
        
        # Esegui il solver con questa combinazione di parametri
        solver_output, output_file_path, _, parsed_results, return_code, csv_path = solve_parametric_lqn(
            lqn_file_path=lqn_file_path,
            parameter_values=param_dict,
            lqns_binary_path=lqns_binary_path,
            run_solver=True,
            output_dir=session_dir,  # Questo fa salvare i CSV direttamente nella directory della sessione
            save_csv=save_csv  # Controlla se salvare i CSV individuali
        )
        
        if return_code == 0 and parsed_results:
            # Salva CSV solo se richiesto
            if save_csv and csv_path and os.path.exists(csv_path):
                print(f"Salvato risultato in: {csv_path}")
                csv_files.append(csv_path)
            
            # Salva i risultati per il file MATLAB indipendentemente dal salvataggio CSV
            all_parsed_results.append(parsed_results)
            all_parameter_values.append(param_dict)
            
            # Estrai metriche per il riepilogo
            max_proc_util = 0
            bottleneck_proc = ""
            for proc, util in parsed_results['processor_utilization'].items():
                if util > max_proc_util:
                    max_proc_util = util
                    bottleneck_proc = proc
            
            max_resp_time = 0
            bottleneck_entry = ""
            for entry, resp_time in parsed_results['entry_response_time'].items():
                if resp_time > max_resp_time:
                    max_resp_time = resp_time
                    bottleneck_entry = entry
            
            # Aggiorna il file di riepilogo
            with open(summary_file_path, 'a', newline='') as summary_file:
                summary_writer = csv.writer(summary_file)
                row = [param_dict[param] for param in param_names]
                row.extend([max_proc_util, bottleneck_proc, max_resp_time, bottleneck_entry])
                if save_csv and csv_path:
                    row.append(os.path.basename(csv_path))
                summary_writer.writerow(row)
            
            # Aggiorna il log
            with open(log_file_path, 'a') as log_file:
                log_file.write(f"  Successo. Bottleneck: {bottleneck_proc} ({max_proc_util:.4f}), Entry: {bottleneck_entry} ({max_resp_time:.4f})\n")
        else:
            print(f"Errore nell'esecuzione di questa combinazione (codice {return_code})")
            with open(log_file_path, 'a') as log_file:
                log_file.write(f"  Errore (codice {return_code})\n")
        
        # Stima tempo rimanente
        elapsed = time.time() - start_time
        avg_time_per_combo = elapsed / (i + 1)
        remaining_combos = len(combinations) - (i + 1)
        est_remaining_time = avg_time_per_combo * remaining_combos
        
        print(f"Progresso: {i+1}/{len(combinations)} ({(i+1)/len(combinations)*100:.1f}%)")
        print(f"Tempo stimato rimanente: {est_remaining_time/60:.1f} minuti")
    
    # Salva tutti i risultati in un unico file MATLAB
    matlab_file_path = None
    if all_parsed_results:
        matlab_file_path = save_results_to_matlab(
            all_parsed_results, 
            all_parameter_values, 
            lqn_file_path,
            session_dir
        )
    
    total_time = time.time() - start_time
    print(f"\nProfiling completato in {total_time/60:.1f} minuti")
    
    if save_csv:
        print(f"Generati {len(csv_files)} file CSV")
    
    if matlab_file_path:
        print(f"Risultati aggregati salvati in formato MATLAB: {matlab_file_path}")
    
    print(f"Riepilogo salvato in: {summary_file_path}")
    print(f"Log dettagliato in: {log_file_path}")
    
    # Aggiorna il log finale
    with open(log_file_path, 'a') as log_file:
        log_file.write(f"\nProfiling completato in {total_time/60:.1f} minuti\n")
        if save_csv:
            log_file.write(f"Generati {len(csv_files)} file CSV\n")
        if matlab_file_path:
            log_file.write(f"Risultati aggregati salvati in formato MATLAB: {matlab_file_path}\n")
    
    return csv_files, matlab_file_path

def main():
    """Parses command-line arguments and runs the parametric LQN solver."""
    parser = argparse.ArgumentParser(description="Solve a parametric LQN file by substituting variables and running lqns.")
    
    parser.add_argument("lqn_file", help="Path to the input parametric .lqn file.")
    parser.add_argument("--param", action='append', metavar='NAME=VALUE',
                        help="Parameter assignment (e.g., N_USERS=10). Can be specified multiple times.")
    parser.add_argument("--solver-path", default="lqns", 
                        help="Path to the lqns solver executable (default: lqns in PATH).")
    
    args = parser.parse_args()
    
    # Convert parameter assignments to a dictionary
    parameter_values = {}
    if args.param:
        for p in args.param:
            try:
                name, value = p.split('=', 1)
                # Attempt to convert value to int or float, keep as string if error
                try:
                    num_value = float(value) if '.' in value else int(value)
                    parameter_values[name.strip()] = num_value
                except ValueError:
                    print(f"Warning: Could not convert value '{value}' for parameter '{name}' to number. Keeping as string.")
                    parameter_values[name.strip()] = value # Keep as string if conversion fails
            except ValueError:
                print(f"Error: Invalid parameter format '{p}'. Use NAME=VALUE.")
                return # Exit if format is wrong

    print(f"Input LQN file: {args.lqn_file}")
    print(f"Parameter values: {parameter_values}")
    print(f"Solver path: {args.solver_path}")

    # Call the main function with parsed arguments
    csv_files, matlab_file = profile_lqn(
        lqn_file_path=args.lqn_file,
        parameter_ranges=parameter_values,
        lqns_binary_path=args.solver_path,
        output_dir=None,  # Assuming no specific output directory is provided
        save_csv=True  # Set to True to save individual CSV files
    )

    if matlab_file:
        print(f"\nRisultati salvati in formato MATLAB: {matlab_file}")

# Example Usage (can be run directly if needed)
if __name__ == '__main__':
    #main()
    lqn_file_path = "LQNs/20250430/lqn01-5f.lqn"
    # parameter_values = {
    # 'N_USERS': 5,
    # 'N_CORES_1': 2,
    # 'N_CORES_2': 3,
    # 'N_CORES_3': 4,
    # 'N_CORES_4': 5,
    # }
    # solver_output, output_file_path, parseable_output_path, parsed_results, return_code, csv_path = solve_parametric_lqn(lqn_file_path,
    #                                    parameter_values,
    #                                    run_solver=True,
    #                                    lqns_binary_path="/Users/emilio-imt/git/V6/lqns/lqns")
    
    # if csv_path:
    #     print(f"\nRisultati salvati in CSV: {csv_path}")
    
    # Esempio di utilizzo di profile_lqn:
    parameter_ranges = {
        'N_USERS': range(1,30,5),  # 5 possibili valori
        'N_CORES_1': [1,100],         # 3 possibili valori
        'N_CORES_2': [1,100],         # 3 possibili valori
        'N_CORES_3': [1,100]         # 3 possibili valori
    }
    csv_files, matlab_file = profile_lqn(
        lqn_file_path="LQNs/20250430/lqn01-5f.lqn",
        parameter_ranges=parameter_ranges,
        lqns_binary_path="/Users/emilio-imt/git/V6/lqns/lqns",
        save_csv=False  # Set to False to not save individual CSV files
    )
    
    if matlab_file:
        print(f"\nRisultati salvati in formato MATLAB: {matlab_file}")
    

