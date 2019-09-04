n = int(input())
a = list(int(input()) for _ in range(n))

c = 0
for i in range(n):
    if a[i] > 0:
        if i > 0 and a[i - 1] > 0:
            c += 1
            a[i] -= 1

        c += a[i] // 2
        a[i] %= 2

print(c)