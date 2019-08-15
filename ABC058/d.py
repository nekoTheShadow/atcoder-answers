n, m = map(int, input().split())
x = list(map(int, input().split())) # n
y = list(map(int, input().split())) # m

x.sort(reverse=True)
y.sort(reverse=True)

s = sum(x[i] * (n - 1 - 2 * i) for i in range(n))
t = sum(y[i] * (m - 1 - 2 * i) for i in range(m))
print((s * t) % (10 ** 9 + 7))