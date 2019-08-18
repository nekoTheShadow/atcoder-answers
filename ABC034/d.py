n, k = map(int, input().split())
w = [None] * n
p = [None] * n
for i in range(n):
    w[i], p[i] = map(float, input().split())

l = 0
r = 100
for _ in range(1000):
    m = (l + r) / 2
    t = list(sorted(map(lambda i: w[i] * (p[i] - m), range(n)), reverse=True))
    if sum(t[:k]) >= 0:
        l = m
    else:
        r = m
    
print(l)