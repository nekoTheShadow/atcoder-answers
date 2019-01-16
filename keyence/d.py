import itertools

def main():
    n, m = map(int, input().split())
    a = set(map(int, input().split()))
    b = set(map(int, input().split()))

    if len(a) != n or len(b) != m:
        return 0
     
    ans = 1
    a_cnt = b_cnt = empty = 0
    mod = 10 ** 9 + 7
    for x in range(n * m, 0, -1):
        if x in a and x in b:
            a_cnt += 1
            b_cnt += 1
            empty += (a_cnt - 1) + (b_cnt - 1)
        elif x in a:
            a_cnt += 1
            empty += b_cnt
            ans = (ans * b_cnt) % mod
            empty -= 1
        elif x in b:
            b_cnt += 1
            empty += a_cnt
            ans = (ans * a_cnt) % mod
            empty -= 1
        else:
            ans = (ans * empty) % mod
            empty -= 1
    
    return ans

if __name__ == '__main__':
    print(main())

