s = input()
k = int(input())

t = []
for i in range(len(s)):
    d = 0 if s[i] == 'a' else ord('z') - ord(s[i]) + 1 
    if d <= k:
        t.append('a')
        k -= d
    else:
        t.append(s[i])

k %= 26
for _ in range(k):
    if t[-1] == 'z':
        t[-1] = 'a'
    else:
        t[-1] = chr(ord(t[-1]) + 1)

print(''.join(t))