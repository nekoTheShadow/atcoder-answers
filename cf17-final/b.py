def ok(s):
    a = b = c = 0
    for ch in s:
        if ch == 'a': a += 1
        if ch == 'b': b += 1
        if ch == 'c': c += 1
    return abs(a - b) <= 1 and abs(b - c) <= 1 and abs(c - a) <= 1

s = input()
if ok(s):
    print('YES')
else:
    print('NO')