imos = [0] * (24 * 60 + 2)
n = int(input())
for _ in range(n):
    line = input()
    h1, m1, h2, m2 = (int(line[i:i+2]) for i in (0, 2, 5, 7))
    while m1 % 5 != 0: m1 -= 1
    while m2 % 5 != 0: m2 += 1
    imos[h1 * 60 + m1] += 1 
    imos[h2 * 60 + m2 + 1] -= 1

for i in range(24 * 60 + 1):
    imos[i + 1] += imos[i]

scope = []
for i in range(24 * 60 + 2):
    if len(scope) == 0 and imos[i] != 0:
        scope.append(i)
    if len(scope) == 1 and imos[i] == 0:
        scope.append(i - 1)
    if len(scope) == 2:
        h1, m1 = scope[0] // 60, scope[0] % 60
        h2, m2 = scope[1] // 60, scope[1] % 60
        print('{:02}{:02}-{:02}{:02}'.format(h1, m1, h2, m2))
        scope.clear()