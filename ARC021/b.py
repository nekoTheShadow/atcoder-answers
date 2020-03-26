l = int(input())
b = [int(input()) for _ in range(l)]

a = [0]
for i in range(l-1):
    a.append(a[i] ^ b[i])

if (a[0] ^ a[-1]) == b[-1]:
    for v in a:
        print(v)
else:
    print(-1)
