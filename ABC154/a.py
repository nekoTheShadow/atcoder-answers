s, t = input().split()
a, b = map(int, input().split())
u = input()

d = {s : a, t : b}
d[u] -= 1
print(d[s], d[t])