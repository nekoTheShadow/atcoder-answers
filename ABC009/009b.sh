#!/bin/bash
read n
sort -nr | uniq | awk 'NR==2'