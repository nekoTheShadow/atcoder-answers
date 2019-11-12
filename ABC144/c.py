n = int(input())

x = 1
ans = float('inf')
while x * x <= n:
    if n % x == 0:
        y = n // x
        ans = min(ans, x - 1 + y - 1)
    x += 1
print(ans)
