x, n = map(int, input().split())
p = set(map(int, input().split()))

d = 0
while True:
    if not x - d in p:
        print(x - d)
        break
    
    if not x + d in p:
        print(x + d)
        break
    
    d += 1
