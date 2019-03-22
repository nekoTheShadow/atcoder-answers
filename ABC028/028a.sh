#!/bin/bash

# テストの点数として 0 以上 100以下の整数を受け取る。
# テストの点数が 59点以下ならば Bad と出力。
# 60点以上 89点以下ならば Good と出力。
# 90点以上 99点以下ならば Great と出力。
# 100点ならば Perfect と出力。

read n
if ((n <= 59)); then
  echo "Bad"
elif ((60 <= n && n <= 89)); then
  echo "Good"
elif ((90 <= n && n <= 99)); then
  echo "Great"
else # n=100
  echo "Perfect"
fi
