#!/bin/bash

read n r
if ((n < 10)); then
  echo $((r + 100*(10-n)))
else
  echo ${r}
fi