n = int(input())

adds = []
muls = []

for _ in range(n):
    c, a = input().split()
    a = int(a)
    if c == '+':
        adds.append(a)
    if c == '*' and a > 0:
        muls.append(a)

ans = 0
for add in adds:
    ans += add
for mul in muls:
    ans *= mul
print(ans)



