#!/bin/bash
read s
[[ ${s: -1} == "T" ]] && echo "YES" || echo "NO"