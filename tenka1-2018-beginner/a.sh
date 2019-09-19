#!/bin/bash

read s
len=${#s}
if ((len == 2)); then
  echo ${s}
else
  echo ${s} | rev
fi