import collections

if __name__ == '__main__':
    n, m = map(int, input().split())

    counter = collections.defaultdict(int)
    for _ in range(m):
        a, b = map(int, input().split())
        counter[a] += 1
        counter[b] += 1

    for i in range(1, n + 1):
        print(counter[i])
