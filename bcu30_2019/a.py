n, k = map(int, input().split())
a = [int(input()) for _ in range(n)]

a.sort(reverse=True)
ans = sum(a[:k]) + sum(a[k:]) * 2
print(ans)
