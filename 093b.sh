#!/bin/bash

read a b k
{
  seq ${a} ${b} | head -n ${k}
  seq ${a} ${b} | tail -n ${k}
} | sort -n | uniq