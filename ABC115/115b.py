n = int(input())
prices = [int(input()) for _ in range(n)]
prices.sort()

answer = sum(prices[0:-1]) + (prices[-1] // 2)
print(answer)

