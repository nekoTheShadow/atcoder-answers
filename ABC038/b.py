h1, w1 = map(int, input().split())
h2, w2 = map(int, input().split())

s1 = {h1, w1}
s2 = {h2, w2}
print('YES' if s1 & s2 else 'NO')