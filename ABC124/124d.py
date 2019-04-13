n, k = map(int, input().split())
s = input()

s += 'X'

i = 0
j = 0
zero = 1 if s[0] == '0' else 0
ans = 0
while j < n:
    if zero <= k:
        ans = max(ans, j - i + 1)
        if s[j] == '1' and s[j + 1] == '0':
            zero += 1
        j += 1
    else:
        if s[i] == '0' and s[i + 1] == '1':
            zero -= 1
        i += 1

print(ans)