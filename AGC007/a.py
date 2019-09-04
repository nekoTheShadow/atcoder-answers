h, w = map(int, input().split())
a = [input() for _ in range(h)]

black = sum(s.count('#') for s in a)
if black == h + w - 1:
    print('Possible')
else:
    print('Impossible')