import copy

n, x = map(int, input().split())
a = list(map(int, input().split()))
b = copy.deepcopy(a)

for i in range(n - 1):
    if a[i] + a[i + 1] > x:
        a[i + 1] -= min(a[i + 1], a[i] + a[i + 1] - x)
        if a[i] > x:
            a[i] -= a[i] - x

ans = sum(abs(x - y) for x, y in zip(a, b))
print(ans)