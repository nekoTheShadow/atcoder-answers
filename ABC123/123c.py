import math

n = int(input())
a = int(input())
b = int(input())
c = int(input())
d = int(input())
e = int(input())

f = math.ceil(n / min((a, b, c, d, e))) + 4
print(f)