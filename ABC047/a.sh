#!/bin/bash

read a b c

if (( a + b == c )) || (( a + c == b )) || (( b + c == a )); then
  echo 'Yes'
else
  echo 'No'
fi