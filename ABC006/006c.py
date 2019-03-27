def solve(n, m):
    p = m - 2 * n
    if p < 0:
        return None
    
    y = 0 if p % 2 == 0 else 1
    z = (p - y) // 2
    if z < 0:
        return None
    
    x = n - y - z
    if x < 0:
        return None
    return (x, y, z)

if __name__ == '__main__':
    n, m = map(int, input().split())
    tp = solve(n, m)
    if tp is None:
        tp = (-1, -1, -1)
    print(' '.join(map(str, tp)))