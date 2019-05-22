import math

def solve(h, alist, blist):
    amax = max(alist)
    c = 0
    for b in sorted(blist, reverse=True):
        if b <= amax:
            break
        c += 1
        h -= b
        if h <= 0:
            return c
    return c + math.ceil(h / amax)
 
 
n, h = map(int, input().split())
alist, blist = [], []
for _ in range(n):
    a, b = map(int, input().split())
    alist.append(a)
    blist.append(b)
 
print(solve(h, alist, blist))