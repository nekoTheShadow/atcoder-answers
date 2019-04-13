n = int(input())
h = list(map(int, input().split()))

ans = 1
for i in range(1, n):
    if all(h[j] <= h[i] for j in range(0, i)):
        ans += 1

print(ans)