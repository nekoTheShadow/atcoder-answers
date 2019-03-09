#!/bin/bash

read x a b
if (( b - a <= 0 )); then
  echo "delicious"
elif (( b - a <= x )); then
  echo "safe"
else
  echo "dangerous"
fi
