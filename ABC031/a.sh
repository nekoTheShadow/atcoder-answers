#!/bin/bash

read a d
x=$((a * (d + 1)))
y=$(((a + 1) * d))

if (( x < y )); then
  echo ${y}
else
  echo ${x}
fi