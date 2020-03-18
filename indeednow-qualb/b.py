s = input()
t = input()
n = len(s)

if s == t:
    print(0)
    exit(0)

for i in range(n):
    if t == s[n-i-1:] + s[:n-i-1]:
        print(i + 1)
        exit(0)
print(-1)