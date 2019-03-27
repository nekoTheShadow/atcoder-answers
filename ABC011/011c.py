n = int(input())
ng = [int(input()) for _ in range(3)]
memo = {}

def solve(x, count):
    key = (x, count)
    if key in memo:
        return memo[key]

    if x in ng or x < 0 or count < 0:
        return False
    if x == 0:
        return True
    
    memo[key] = any(solve(x - i, count - 1) for i in (1, 2, 3))
    return memo[key]

print('YES' if solve(n, 100) else 'NO')