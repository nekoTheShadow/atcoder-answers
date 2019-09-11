import collections

s = input()
v = collections.deque(s)
c = 0
while len(v) > 1:
    x = v[0]
    y = v[-1]
    if x == y:
        v.popleft()
        v.pop()
    else:
        if x == 'x':
            c += 1
            v.popleft()
        elif y == 'x':
            c += 1
            v.pop()
        else:
            print(-1)
            exit()

print(c)