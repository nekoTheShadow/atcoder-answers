n, k = list(map(int, input().split()))
a = list(map(int, input().split()))
f = list(map(int, input().split()))

a.sort(reverse=True)
f.sort(reverse=False)

def isok(mid):
    total = 0
    for i in range(n):
        if a[i] * f[i] <= mid:
            continue
        total += a[i] - mid // f[i]
    return total <= k

ng = -1
ok = a[0] * f[-1] + 1
while abs(ok - ng) > 1:
    mid = (ok + ng) // 2
    if isok(mid):
        ok = mid
    else:
        ng = mid

print(ok)