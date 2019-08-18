#!/bin/bash

read x t
s=$((x - t))
((s > 0)) && echo ${s} || echo 0 
