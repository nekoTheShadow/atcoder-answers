import collections

if __name__ == '__main__':
    n, k = map(int, input().split())
    counter = collections.defaultdict(int)

    for _ in range(n):
        a, b = map(int, input().split())
        counter[a] += b

    x = 0
    for i in sorted(counter):
        if x <= k <= x + counter[i]:
            print(i)
            break
        x += counter[i]
