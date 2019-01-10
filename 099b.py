a, b = map(int, input().split())

t1, t2, s = 1, 1 + 2, 3
while not (t1 - a > 0 and t2 - b > 0 and t1 - a == t2 - b):
    t1, t2, s = t2, t2 + s, s + 1

x = t1 - a
print(x)