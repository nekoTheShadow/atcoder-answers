#!/bin/bash

read n a b
p1=$(( a * n ))
if (( p1 < b )); then
  echo ${p1}
else
  echo ${b}
fi