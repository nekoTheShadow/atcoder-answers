n, k = map(int, input().split())
t = n // 2 if n % 2 == 0 else n // 2 + 1
ans = 'YES' if k <= t else 'NO'
print(ans) 