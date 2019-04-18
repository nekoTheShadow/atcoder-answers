def gcd(a, b):
    while a % b > 0:
        a, b = b, a % b
    return b

w, h = map(int, input().split())
d = gcd(w, h)
w //= d
h //= d
if w == 4 and h == 3:
    print('4:3')
else:
    print('16:9')