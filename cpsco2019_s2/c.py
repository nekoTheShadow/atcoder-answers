n, k = map(int, input().split())
s = input()

cost = 0
costs = []
for i in range(n - 1):
    if s[i] == '(':
        cost += 1
    else:
        cost -= 1
    costs.append(cost)

costs.sort(reverse=True)
ans = 0
for i in range(k):
    ans += costs[i]
print(ans)