#!/bin/bash
read n
sort | uniq -c | sort -nr | awk 'NR==1{print $2}'