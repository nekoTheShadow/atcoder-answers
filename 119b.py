n = int(input())
xlist = []
ulist = []
for _ in range(n):
    x, u = input().split()
    xlist.append(float(x))
    ulist.append(u)

ans = 0
for x, u in zip(xlist, ulist):
    if u == 'JPY':
        ans += x
    else: # u == 'BTC'
        ans += x *  380000.0

print(ans)

