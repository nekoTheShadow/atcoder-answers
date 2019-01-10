n = int(input())
a = list(map(int, input().split()))

l = r = now = ans = 0
while r < n:
    if now + a[r] == now ^ a[r]:
        now += a[r]
        r += 1
        ans += r - l
    else:
        now -= a[l]
        l += 1

print(ans)