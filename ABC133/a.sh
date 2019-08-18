#!/bin/bash

read n a b
x=$(( n * a ))
(( x < b )) && echo ${x} || echo ${b}