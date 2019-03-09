import itertools

n = int(input())
f = [list(map(int, input().split())) for _ in range(n)]
p = [list(map(int, input().split())) for _ in range(n)]

answers = []
for bit in range(1, 2 ** 10):
    t = [min(1, bit & (1 << i)) for i in range(10)]
    answer = 0
    for i in range(n):
        c = sum(1 for j in range(10) if f[i][j] == 1 and t[j] == 1)
        answer += p[i][c]
    answers.append(answer)

print(max(answers))
