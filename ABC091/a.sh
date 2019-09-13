#!/bin/bash

read a b c
if (( a + b >= c )); then
  echo 'Yes'
else
  echo 'No'
fi