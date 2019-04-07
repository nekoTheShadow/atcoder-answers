import itertools

a = int(input())
b = int(input())
c = int(input())
d = int(input())
e = int(input())
k = int(input())

v = (a, b, c, d, e)
if all(abs(v1 - v2) <= k for v1, v2 in itertools.product(v, repeat=2)):
    print('Yay!')
else:
    print(':(')