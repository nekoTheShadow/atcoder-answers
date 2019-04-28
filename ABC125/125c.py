def gcd(a, b):
    a, b = min(a, b), max(a, b)
    while b % a != 0:
        a, b = b % a, a
    return a


n = int(input())
a = list(map(int, input().split()))

# s = [-1, *a[:-1]]
# t = [*a[1:], -1]
s = [-1] * n
t = [-1] * n
for x in range(0, n - 1):
    s[x + 1] = a[x]
for x in range(1, n):
    t[x - 1] = a[x]

for x in range(1, n - 1):
    s[x + 1] = gcd(s[x + 1], s[x])
for x in range(n - 2, 0, -1):
    t[x - 1] = gcd(t[x - 1], t[x])

ans = -float('inf')
for x, y in zip(s, t):
    if x == -1:
        ans = max(ans, y)
    elif y == -1:
        ans = max(ans, x)
    else:
        ans = max(ans, gcd(x, y))
    
print(ans)