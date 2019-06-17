#!/bin/bash

read x a
if (( x < a )); then
  echo 0
else
  echo 10
fi