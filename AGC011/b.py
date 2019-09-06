import bisect, copy

n = int(input())
a = list(map(int, input().split()))
a.sort()

b = copy.deepcopy(a)
for i in range(n - 1):
    b[i + 1] += b[i]

def ok(x):
    while True:
        y = bisect.bisect_right(a, b[x] * 2)
        if x == y - 1:
            return False
        if y == n:
            return True
        x = y - 1
    

lo = -1
hi = n
while abs(lo - hi) > 1:
    mi = (lo + hi) // 2
    if ok(mi):
        hi = mi
    else:
        lo = mi

print(n - hi)