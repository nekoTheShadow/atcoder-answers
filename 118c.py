import sys

n = int(input())
a = list(map(int, input().split()))

a.sort()
answer = a[0]
for i in range(1, n):
    if a[i] % a[0] == 0: continue

    x, y = a[0], a[i] # x < y
    while x != 0:
        x, y = y % x, x
    answer = min(answer, y)

print(answer)



# ans = float('inf')
# for i in range(n):
#     for j in range(i + 1, n):
#         ans = min(ans, a[i] - a[j] % a[i])

# print(ans)