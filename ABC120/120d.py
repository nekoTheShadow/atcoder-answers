class UnionFind(object):
    def __init__(self, size):
        self.size = size
        self.parents = list(range(size))
        self.counts = [1] * size

    def find(self, x):
        history = []
        while self.parents[x] != x:
            history.append(x)
            x = self.parents[x]
        
        for i in history: 
            self.parents[i] = x
        return x
    
    def union(self, x1, x2):
        y1 = self.find(x1)
        y2 = self.find(x2)
        if y1 != y2:
            self.parents[y2] = y1
            self.counts[y1] += self.counts[y2]

    def count(self, x):
        return self.counts[self.find(x)]
    
if __name__ == '__main__':
    n, m = map(int, input().split())
    alist = []
    blist = []
    for _ in range(m):
        a, b = map(int, input().split())
        alist.append(a - 1)
        blist.append(b - 1)

    answers = [n * (n - 1) // 2]
    uf = UnionFind(n)

    for a, b in zip(reversed(alist), reversed(blist)):
        answers.append(answers[-1])
        c = uf.find(a)
        d = uf.find(b)
        if c == d: 
            continue
  
        answers[-1] -= uf.count(a) * uf.count(b)
        uf.union(a, b)

    for i in reversed(range(m)):
        print(answers[i])