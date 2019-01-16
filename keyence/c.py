def main():
    n = int(input())
    a = list(map(int, input().split()))
    b = list(map(int, input().split()))

    if sum(a) < sum(b):
        return -1

    diffs = []
    rest = 0
    ans = 0
    for i in range(n):
        if a[i] < b[i]:
            rest += b[i] - a[i]
            ans += 1
        if a[i] > b[i]:
            diffs.append(a[i] - b[i])
            
    diffs.sort()
    while rest > 0:
        diff = diffs.pop()
        rest -= diff
        ans += 1

    return ans


if __name__ == '__main__':
    print(main())