n = int(input())
k = int(input())
x = list(map(int, input().split()))

ans = sum(min(v * 2, abs(k - v) * 2) for v in x)
print(ans)