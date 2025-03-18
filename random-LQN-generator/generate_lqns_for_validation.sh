#! /bin/bash

python3 lqn_generator_jinja2.py --num-lqns 6 --max-functions 12 --min-functions 6 \
                                --prob-edge 0.6 --prob-async 0 --prob-parallel 0.8

#cp LQNs/* ../resources/wasteless_journal/