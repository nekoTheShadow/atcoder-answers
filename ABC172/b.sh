paste <(head -n1 | fold -s1) <(tail -n1 | fold -s1) | awk '$1!=$2' | wc -l
