import collections

def main():
    t = int(input())
    n = int(input())
    a = collections.deque(map(int, input().split()))
    m = int(input())
    b = list(map(int, input().split()))

    for j in range(m):
        ok = False
        for i in range(n):
            if a[i] <= b[j] <= a[i] + t:
                ok = True
                a[i] = float('inf')
                break
        
        if not ok:
            return 'no'
    
    return 'yes'

if __name__ == '__main__':
    print(main())