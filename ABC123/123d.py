import itertools

x, y, z, k = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
c = list(map(int, input().split()))

d = list(sorted(map(sum, itertools.product(a, b)), reverse=True))
c.sort(reverse=True)
e = list(sorted(map(sum, itertools.product(c[:k], d[:k])), reverse=True))
for v in e[:k]:
    print(v)