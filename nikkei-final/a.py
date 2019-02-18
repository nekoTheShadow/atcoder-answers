n = int(input())
a = list(map(int, input().split()))

totals = [0]
for i in range(n):
    totals.append(totals[-1] + a[i])

answers = []
for k in range(1, n + 1):
    answer = 0
    for i in range(n - k + 1):
        answer = max(answer, totals[i + k] - totals[i])
    answers.append(answer)

for answer in answers:
    print(answer)
