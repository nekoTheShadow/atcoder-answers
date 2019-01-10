#!/bin/bash

read k
if (( k % 2 == 0 )); then
  echo $(( (k / 2) * (k / 2) ))
else
  echo $(( (k / 2 + 1) * (k / 2) ))
fi