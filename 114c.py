import itertools

n = int(input())

stack = list('753')
ans = 0
while stack:
    x = stack.pop()
    
    if n < int(x):
        continue
        
    if all(x.count(c) > 0 for c in '753'):
        ans += 1
    stack.extend(x + c for c in '753')

print(ans)
