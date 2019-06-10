#!/bin/bash

read n
read s
echo ${s} | fold -s1 | awk 'BEGIN{print 0} {print $1 == "I" ? ++x : --x}' | sort -n | tail -n 1