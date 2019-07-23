n = int(input())
x = 1
v = []
while n != 0:
    m = abs(n % 2)
    v.append(m)
    n = (n - m) // -2

if len(v) == 0:
    v.append(0)

print(''.join(map(str, reversed(v))))