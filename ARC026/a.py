n, a, b = map(int, input().split())
c = min(n, 5) * b + max(n - 5, 0) * a
print(c)