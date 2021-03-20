from decimal import Decimal 
import math

x, y, r = map(Decimal, input().split())

ans = 0
for a in range(math.ceil(x-r), math.floor(x+r)+1):
    p = math.ceil(-(r**2 - (a-x)**2).sqrt() + y)
    q = math.floor((r**2 - (a-x)**2).sqrt() + y)
    ans += q-p+1

print(ans)