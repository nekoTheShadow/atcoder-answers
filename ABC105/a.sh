#!/bin/bash

read n k
if (( n % k == 0 )); then
  echo 0
else
  echo 1
fi