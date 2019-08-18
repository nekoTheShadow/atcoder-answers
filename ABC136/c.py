n = int(input())
h = list(map(int, input().split()))

for i in range(n - 1, 0, -1):
    if h[i - 1] > h[i]:
        h[i - 1] -= 1

ans = 'Yes' if all(h[i] <= h[i + 1] for i in range(0, n - 1)) else 'No'
print(ans)