#!/bin/bash
read n
echo $n | fold -s1 | tac | awk 'BEGIN{a=b=0} NR%2==0{a+=$0} NR%2!=0{b+=$0} END{print a,b}'

