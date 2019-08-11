n = int(input())
a = list(map(int, input().split()))

total = sum(a)
x = 0
ans = float('inf')
for i in range(n - 1):
    x += a[i]
    y = total - x
    ans = min(ans, abs(x - y))

print(ans)