#!/bin/bash
read n k 
sed 'y/ /\n/' | sort -n | head -n ${k} | paste -sd+ | bc