read s
echo "ABCDEF${s}" | fold -s1 | sort | uniq -c | awk '{print $1 - 1}' | paste -sd' '