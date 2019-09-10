n = int(input())
b= list(map(int, input().split()))

v = []
while b:
    x = -1
    for i in range(len(b)):
        if b[i] == i + 1:
            x = i

    if x == -1:
        print(-1)
        exit()

    v.append(b[x])
    del b[x]

for e in reversed(v):
    print(e)