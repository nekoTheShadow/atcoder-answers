a, b, c, d = map(int, input().split())
l = a + b
r = c + d
if l > r:  print('Left')
if l == r: print('Balanced')
if l < r:  print('Right')