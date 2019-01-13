#!/bin/bash
echo $(( 700 + $(fold -s1 | grep -c 'o') * 100 ))