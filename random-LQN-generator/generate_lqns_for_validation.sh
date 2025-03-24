#! /bin/bash

python3 lqn_generator_jinja2.py --num-lqns 5 --max-functions 15 --min-functions 7 \
                                --prob-edge 0.3 --prob-async 0 --prob-parallel 1

python3 process_lqns_into_lqx.py

#cp LQNs/* ../resources/wasteless_journal/