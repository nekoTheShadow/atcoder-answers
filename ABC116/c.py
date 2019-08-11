n = int(input())
h = list(map(int, input().split()))

c = 0
for i in range(n):
    while h[i] > 0:
        for j in range(i, n):
            if h[j] == 0:
                break
            h[j] -= 1
        c += 1

print(c)