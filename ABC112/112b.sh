#!/bin/bash

read n t
c=$(awk -v t=${t} '$2<=t' | sort -n | head -n1 | cut -d' ' -f1)
echo ${c:-TLE}