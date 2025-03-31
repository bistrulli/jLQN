#! /bin/bash

python3 lqn_generator_jinja2.py --num-lqns 1 --max-functions 2 --min-functions 2 \
                                --prob-edge 1 --prob-async 0 --prob-parallel 0

python3 process_lqns_into_lqx.py

#cp LQNs/* ../resources/wasteless_journal/