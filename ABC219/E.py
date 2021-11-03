import itertools
from typing import Set, Tuple

def is_connected(tpls: Set[Tuple[int, int]]):
    stack = [tpls.pop()]
    tpls.add(stack[0])
    visited = set()

    while stack:
        (x, y) = stack.pop()
        visited.add((x, y))

        for dx, dy in ((0, 1), (0, -1), (1, 0), (-1, 0)):
            nx = x+dx
            ny = y+dy
            if (nx, ny) in tpls and not (nx, ny) in visited:
                stack.append((nx, ny))
    
    return visited == tpls


a = [list(map(int, input().split())) for _ in range(4)]
villages = set((i+1, j+1) for (i, j) in itertools.product(range(4), repeat=2) if a[i][j]==1)

ans = 0
for bit in range(1, 1<<16):
    inner = set((i+1, j+1) for (i, j) in itertools.product(range(4), repeat=2) if (bit&(1<<(i*4+j)))!=0) 
    outer = set((i, j) for (i, j) in itertools.product(range(6), repeat=2) if not (i, j) in inner)
    if all(village in inner for village in villages) and is_connected(inner) and is_connected(outer): ans += 1

print(ans)

