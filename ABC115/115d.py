N, X = map(int, input().split())

a, p = [1], [1]
for _ in range(N):
    a.append(a[-1] * 2 + 3)
    p.append(p[-1] * 2 + 1)

def f(n, x):
    if n == 0:
        return 0 if x <= 0 else 1
    if x <= a[n - 1] + 1:
        return f(n - 1, x - 1)
    else:
        return p[n - 1] + 1 + f(n - 1, x - a[n - 1] - 2)


print(f(N, X))