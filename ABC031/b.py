l, h = map(int, input().split())
n = int(input())
a = [int(input()) for _ in range(n)]

for i in range(n):
    if a[i] < l:
        print(l - a[i])
    elif l <= a[i] <= h:
        print(0)
    else:
        print(-1)