n = int(input())
a = input()
b = input()
c = input()

ans = 0
for i in range(n):
    s = len(set((a[i], b[i], c[i])))
    if s == 1:
        pass
    if s == 2:
        ans += 1
    if s == 3:
        ans += 2

print(ans)