n = int(input())
a = list(sorted(map(int, input().split())))
b = list(sorted(map(int, input().split())))

c = set(a[0] ^ v for v in b)
ans = set()
for v in c:
    if b == list(sorted(v ^ w for w in a)):
        ans.add(v)

print(len(ans))
for v in sorted(ans):
    print(v)

