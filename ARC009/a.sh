#!/bin/bash

read n
awk '{sum+=$1*$2} END{print(int(sum*1.05))}'