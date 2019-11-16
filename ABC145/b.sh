read n
read s

if (( n % 2 != 0)); then
  echo No
  exit
fi

m=$((n/2))
a=${s:0:$m}
b=${s:$m:$m}
if [[ $a == $b ]]; then
  echo Yes
else
  echo No
fi