n = int(input())
a = list(map(lambda s: int(s) - 1, input().split()))
b = list(map(int, input().split()))
c = list(map(int, input().split()))

ans = 0
for i in range(n):
    ans += b[a[i]]
for i in range(n - 1):
    if a[i] + 1 == a[i+1]:
        ans += c[a[i]]
print(ans)