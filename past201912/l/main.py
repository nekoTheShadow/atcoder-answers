import itertools, math

class UnionFind():
    def __init__(self, n):
        self.n = n
        self.parents = list(range(n))

    def find(self, x):
        if self.parents[x] == x:
            return x
        else:
            self.parents[x] = self.find(self.parents[x])
            return self.parents[x]

    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)
        if x != y:
            self.parents[x] = y
        



n, m = map(int, input().split())
bigs = [tuple(map(int, input().split())) for _ in range(n)]
smalls = [tuple(map(int, input().split())) for _ in range(m)]

ans = 100000000000
for r in range(m+1):
    for combs in itertools.combinations(smalls, r):
        points = [*bigs, *combs]
        edges = []
        for i in range(len(points)):
            for j in range(i+1, len(points)):
                x1, y1, c1 = points[i]
                x2, y2, c2 = points[j]

                d = math.sqrt((x1-x2)**2 + (y1-y2)**2)
                if c1!=c2: d*=10
                edges.append((d, i, j))
        
        uf = UnionFind(len(points))
        edges.sort()
        tot = 0
        for (d, i, j) in edges:
            if uf.find(i) != uf.find(j):
                tot += d
                uf.union(i, j)
        # print(edges, tot)
        ans = min(ans, tot)

print(ans)