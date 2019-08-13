n = int(input())
a = list(map(int, input().split()))

a1 = list(a)
s1 = 0
for i in range(n):
    if i % 2 == 0:
        if s1 + a1[i] >= 0:
            a1[i] = -1 - s1
    else:
        if s1 + a1[i] <= 0:
            a1[i] =  1 - s1
    s1 += a1[i]


a2 = list(a)
s2 = 0
for i in range(n):
    if i % 2 == 0:
        if s2 + a2[i] <= 0:
            a2[i] =  1 - s2
    else:
        if s2 + a2[i] >= 0:
            a2[i] = -1 - s2
    s2 += a2[i]

t1 = sum(abs(x - y) for x, y in zip(a, a1))
t2 = sum(abs(x - y) for x, y in zip(a, a2))
t = min(t1, t2)
print(t)