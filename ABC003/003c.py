n, k = map(int, input().split())
r = list(map(int, input().split()))

r.sort(reverse=True)
c = 0
for i in reversed(range(k)):
    c = (c + r[i]) / 2
print(c)