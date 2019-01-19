import itertools

def solve():
    a, b, c, d = input()
    for bit in range(2 ** 3):
        o1, o2, o3 = ['+' if bit & (1 << i) == 0 else '-' for i in range(3)]
        stmt = ''.join([a,o1,b,o2,c,o3,d])
        if eval(stmt) == 7:
            return stmt + "=7"

print(solve())
