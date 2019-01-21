n = int(input())
a = set(map(int, input().split()))
t = len(a)
print(t - 1 if t % 2 == 0 else t)