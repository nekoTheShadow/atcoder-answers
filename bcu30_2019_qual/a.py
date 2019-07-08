n, p = map(int, input().split())
a = list(map(int, input().split()))

count = 0
for i in range(n):
    if p < a[i]:
        break
    count += 1
    p -= a[i]

print(count)