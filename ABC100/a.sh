#!/bin/bash

read a b
if (( a <= 8 )) && (( b <= 8 )); then
  echo 'Yay!'
else
  echo ':('
fi