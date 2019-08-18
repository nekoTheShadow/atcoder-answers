import collections, bisect

s = input()
t = input()

n = len(s)
d = collections.defaultdict(list)
for i, c in enumerate(s):
    d[c].append(i + 1)

m = len(t)
x = [0] * (m + 1) # 長さを記録
y = [0] * (m + 1) # 添え字を記録

for i in range(1, m + 1):
    c = t[i - 1]
    if not c in d:
        print(-1)
        exit()
    
    a = y[i - 1]
    ptr = bisect.bisect_right(d[c], y[i - 1])
    if ptr == len(d[c]):
        b = d[c][0]
    else:
        b = d[c][ptr]
    
    y[i] = b
    if a < b:
        x[i] = x[i - 1] + b - a
    else:
        x[i] = x[i - 1] + n - a + b
    
print(x[-1])
