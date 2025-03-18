#! /bin/bash


python3 lqn_generator_jinja2.py -n 6 -max 12 -min 6 -p 0.6 -a 1 -v 0.001 -async 0 --p_parallel 0.8

#cp LQNs/* ../resources/wasteless_journal/