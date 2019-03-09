def main():
    n, m = map(int, input().split())
    x = list(sorted(map(int, input().split())))
    if n >= m: return 0

    diffs = list(sorted((abs(x[i + 1] - x[i]) for i in range(m - 1)), reverse=True))
    return sum(diffs[i] for i in range(n - 1, m - 1))

print(main())