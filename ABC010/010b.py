n = int(input())
ans = 0
for a in map(int, input().split()):
    while not a % 6 in (1, 3):
        ans += 1
        a -= 1
print(ans)
