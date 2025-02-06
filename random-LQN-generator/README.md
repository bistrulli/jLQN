# random-LQN-generator

A Python program that allows you to generate random LQN files (format .lqn).


usage: lqn_generator.py [-h] [-n NUMBER] -f FUNCTIONS [-a CALL_AVG]
                        [-v CALL_VAR]

Generate a set of random LQNs.

options:
  -h, --help            show this help message and exit
  -n NUMBER, --number NUMBER
                        The number of LQNs to generate (default is 10).
  -f FUNCTIONS, --functions FUNCTIONS
                        The number of functions for each LQN.
  -a CALL_AVG, --call_avg CALL_AVG
                        The average number of calls for each task.
  -v CALL_VAR, --call_var CALL_VAR
                        The variance of calls for each task.
