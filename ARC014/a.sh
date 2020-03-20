#!/bin/bash

read n
if ((n%2==0)); then
  echo "Blue"
else
  echo "Red"
fi