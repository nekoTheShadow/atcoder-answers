import math

n = int(input())
ans = sum(1 for i in range(1, n + 1) if (math.floor(math.log10(i)) + 1) % 2 != 0)
print(ans)