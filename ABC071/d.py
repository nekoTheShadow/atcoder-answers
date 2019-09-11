n = int(input())
s = [input() for _ in range(2)]

if s[0][0] == s[1][0]:
    c = 3
    last = 'x'
    p = 1
else:
    c = 6
    last = 'y'
    p = 2

while p < n:
    if s[0][p] == s[1][p]:
        if last == 'x':
            c *= 2
        else:
            c *= 1
        last = 'x'
        p += 1
    else:
        if last == 'x':
            c *= 2
        else:
            c *= 3
        last = 'y'
        p += 2
    c %= 10 ** 9 + 7

print(c)