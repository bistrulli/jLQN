#! /bin/bash

python3 lqn_generator_jinja2.py --num-lqns 3 --max-functions 6 --min-functions 6 \
                                --prob-edge 0.6 --prob-async 0 --prob-parallel 0

#python3 process_lqns_into_lqx.py

#cp LQNs/* ../resources/wasteless_journal/