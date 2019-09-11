#!/bin/bash

read s
c=$(echo ${s} | grep -c "AC")
((c > 0)) && echo "Yes" || echo "No"