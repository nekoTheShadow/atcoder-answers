s = input()

n = len(s)
d = [0] * n

x = 0
for i in reversed(range(n)):
    if s[i] == 'L':
        x = 0
    else:
        x += 1
        d[i] = x

x = 0
for i in range(n):
    if s[i] == 'R':
        x = 0
    else:
        x += 1
        d[i] = x


counter = [0] * n
for i in range(n):
    if s[i] == 'R':
        if d[i] % 2 == 0:
            counter[i + d[i]] += 1
        else:
            counter[i + d[i] - 1] += 1
    else:
        if d[i] % 2 == 0:
            counter[i - d[i]] += 1
        else:
            counter[i - d[i] + 1] += 1

print(' '.join(map(str, counter)))