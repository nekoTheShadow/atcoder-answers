n = int(input())
v = list(map(int, input().split()))
c = list(map(int, input().split()))

ans = 0
for i in range(n):
    t = v[i] - c[i]
    if t > 0:
        ans += t

print(ans)