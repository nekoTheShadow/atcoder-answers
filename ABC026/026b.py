import math

n = int(input())
r = [int(input()) for _ in range(n)]

ans = 0
for i, x in enumerate(reversed(sorted(r))):
    if i % 2 == 0:
        ans += x * x
    else:
        ans -= x * x
    
print(ans * math.pi)
