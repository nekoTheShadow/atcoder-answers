n, m, q = map(int, input().split())

matrix = [[0] * (n + 1) for _ in range(n + 1)]
for _ in range(m):
    l, r = map(int, input().split())
    matrix[l][r] += 1

totals = [[0] * (n + 1) for _ in range(n + 1)]
for i in range(1, n + 1):
    for j in range(1, n + 1):
        totals[i][j] += totals[i][j - 1] + matrix[i][j]

answers = []
for _ in range(q):
    p, q = map(int, input().split())
    answer = sum(totals[i][q] - totals[i][p - 1] for i in range(p, q + 1))
    answers.append(answer)

for answer in answers:
    print(answer)
