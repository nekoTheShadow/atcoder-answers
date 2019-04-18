import collections

s = input()
t = int(input())

x = y = z = 0
for c in s:
    if c == 'L':
        x -= 1
    if c == 'R':
        x += 1
    if c == 'U':
        y += 1
    if c == 'D':
        y -= 1
    if c == '?':
        z += 1

d = abs(x) + abs(y)
if t == 1:
    d += z
if t == 2:
    if d < z:
        d = (z - d) % 2
    else:
        d = d - z

print(d)

