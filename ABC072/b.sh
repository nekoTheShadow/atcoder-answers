#!/bin/bash

read s
echo ${s} | fold -s1 | awk 'NR%2' | paste -sd'\0'
