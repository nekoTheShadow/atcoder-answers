a, b, c, d = map(int, input().split())

r1 = b / a
r2 = d / c

if r1 == r2:
    print('DRAW')
else:
    print('TAKAHASHI' if r2 < r1 else 'AOKI')