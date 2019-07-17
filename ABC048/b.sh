#!/bin/bash

read a b x

(( a % x != 0 )) && a=$(( (a / x + 1) * x ))
(( b % x != 0 )) && b=$(( b / x * x ))
echo $(( b / x - a / x + 1 ))