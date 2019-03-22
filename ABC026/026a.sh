#!/bin/bash

read a
seq 0 ${a} | while read b; do echo $((b * (a - b))); done | sort -nr | head -n1