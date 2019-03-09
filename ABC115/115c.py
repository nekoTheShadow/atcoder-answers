n, k = map(int, input().split())
trees = [int(input()) for _ in range(n)]
trees.sort()

answer = min(trees[x + k - 1] - trees[x] for x in range(0, n - k + 1))
print(answer)