#!/bin/bash
read n
read a b 
read k
read p
echo "${a} ${p} ${b}" | sed 's/ /\n/g' | sort -n | uniq -c | awk 'BEGIN{ok=1} $1>1{ok=0} END{print ok ? "YES" : "NO"}'