r, D, x2000 = map(int, input().split())
x = [x2000]
for _ in range(10):
    x.append(x[-1] * r - D)

for i in range(1, 11):
    print(x[i])