def main(k, a, b):
    if k < a:
        return k + 1
    
    t = a
    k -= a - 1
    if b - a >= 2:
        t += (b - a) * (k // 2)
        k -= (k // 2) * 2
    
    t += k
    return t


k, a, b = map(int, input().split())
print(main(k, a, b))