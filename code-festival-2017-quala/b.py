n, m, k = map(int, input().split())

for x in range(n + 1):
    for y in range(m + 1):
        black = x * (m - y) + y * (n - x)
        if black == k:
            print('Yes')
            exit(0)
print('No')