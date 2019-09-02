k, n = map(int, input().split())
v = []
w = []
for i in range(n):
    line = input().split()
    v.append(line[0])
    w.append(line[1])

def f(sizes):
    d = {}
    for i in range(n):
        choice_sizes = [sizes[int(c)] for c in v[i]]
        if sum(choice_sizes) != len(w[i]):
            return None
        start = 0
        for size, ky in zip(choice_sizes, v[i]):
            val = w[i][start:start+size]
            if ky in d and d[ky] != val:
                return None
            else:
                d[ky] = val
            start += size
    return d

for bit in range(3 ** k):
    sizes = [0]
    for _ in range(k):
        sizes.append(bit % 3 + 1)
        bit //= 3
    
    d = f(sizes)
    if d != None:
        for ky in sorted(d.keys()):
            print(d[ky])
        exit(0)