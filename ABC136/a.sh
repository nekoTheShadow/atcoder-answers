#!/bin/bash

read a b c
x=$(( c - (a - b) ))
(( x > 0 )) && echo ${x} || echo 0