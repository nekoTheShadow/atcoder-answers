def init(lst):
    l = [lst[0]]
    r = [lst[0]]
    for i in range(1, len(lst)):
        if lst[i - 1] < lst[i]:
            l.append(lst[i])
            r.append(lst[i])
        else:
            l.append(1)
            r.append(lst[i])
    return l, r

n = int(input())
t = list(map(int, input().split()))
a = list(map(int, input().split()))

l1, r1 = init(t)
a.reverse()
l2, r2 = init(a)
l2.reverse()
r2.reverse()

l3 = []
r3 = []
for i in range(n):
    if l2[i] <= r1[i] and l1[i] <= r2[i]:
        l = max(l1[i], l2[i])
        r = min(r1[i], r2[i])
        l3.append(l)
        r3.append(r)
    else:
        print(0)
        exit(0)

ans = 1
for i in range(n):
    ans *= r3[i] - l3[i] + 1
    ans %= 10 ** 9 + 7
print(ans)