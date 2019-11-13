#!/bin/bash

read n
sort -n | tail -1 | awk '{print $1 + $2}'