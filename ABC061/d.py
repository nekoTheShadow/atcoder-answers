n, m = map(int, input().split())
a = []
b = []
c = []
for _ in range(m):
    x, y, z = map(int, input().split())
    a.append(x - 1)
    b.append(y - 1)
    c.append(z * -1)

d = [float('inf')] * n
d[0] = 0

for x in range(n * 2):
    for i in range(m):
        if d[a[i]] + c[i] < d[b[i]]:
            d[b[i]] = d[a[i]] + c[i]
            if n - 1 <= x and b[i] == n - 1:
                print('inf')
                exit(0)

print(d[n - 1] * -1)