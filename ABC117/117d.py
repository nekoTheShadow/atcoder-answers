n, k = map(int, input().split())
a = list(map(int, input().split()))

t = 0
while (k >> t) != 0:
    t += 1

cnts = [0] * t
for i in range(n):
    for j in range(t):
        if (a[i] & (1 << j)) != 0:
            cnts[j] += 1

x = 0
for i in reversed(range(t)):
    p = cnts[i] # ビットが立っている数
    if p < n - p and (x | (1 << i)) <= k:
        x = x | (1 << i)

print(sum(x ^ p for p in a))