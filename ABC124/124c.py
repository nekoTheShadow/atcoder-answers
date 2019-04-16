s = input()

# 0101...のパターン
x1 = 0
for i in range(len(s)):
    t1 = s[i]
    t2 = '0' if i % 2 == 0 else '1'
    if t1 != t2:
        x1 += 1

# 1010...のパターン
x2 = 0
for i in range(len(s)):
    t1 = s[i]
    t2 = '1' if i % 2 == 0 else '0'
    if t1 != t2:
        x2 += 1

print(min(x1, x2))

