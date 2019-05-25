import collections

n, m = map(int, input().split())
alist = list(map(int, input().split()))

counter = collections.defaultdict(int)
for _ in range(m):
    b, c = map(int, input().split()) 
    counter[c] += b

alist.sort()
numbers = list(sorted(counter.keys()))

ans = 0
for a in alist:
    if len(numbers) > 0 and counter[numbers[-1]] == 0:
        numbers.pop()
        
    if len(numbers) > 0 and a < numbers[-1]:
         ans += numbers[-1]
         counter[numbers[-1]] -= 1
    else:
        ans += a

print(ans)