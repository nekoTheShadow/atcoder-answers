#!/bin/bash

read n
read a
echo ${a} | sed 's/\s/\n/g' | sort -nr | awk '(NR%2==0){a+=$0} (NR%2!=0){b+=$0} END{print b-a}'