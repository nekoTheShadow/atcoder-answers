(NR == 1){ n = $0 }
(NR == 2){ a = $0 }
END {print n - int(n / 500) * 500 <= a ? "Yes" : "No"}