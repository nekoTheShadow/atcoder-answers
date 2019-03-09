#!/bin/bash

read n
if [[ ${n} -eq 1 ]]; then
  echo "Hello World"
else
  read a
  read b
  echo $((a + b))
fi