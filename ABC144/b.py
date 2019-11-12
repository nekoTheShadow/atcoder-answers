import itertools

n = int(input())
for i, j in itertools.product(range(1, 10), repeat=2):
    if i*j == n:
        print('Yes')
        exit()
print(('No'))