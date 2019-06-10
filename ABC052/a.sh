read a b c d
echo "$((a * b)) $((c * d))" | tr ' ' '\n' | sort -n | tail -n 1