a, b, k = map(int, input().split())

divs = []
for i in range(1, min(a, b) + 1):
    if a % i == 0 and b % i == 0:
        divs.append(i)

divs.reverse()
print(divs[k - 1])