import itertools

a, b, c, d, e, f = map(int, input().split())

a *= 100
b *= 100
waters = set()
for i in range(0, f // a + 1):
    for j in range(0, (f - a * i) // b + 1):
        waters.add(a * i + b * j)

sugers = set()
g = f // 100 * e
for i in range(0, g // c + 1):
    for j in range(0, (g - c * i) // d + 1):
        sugers.add(c * i + d * j)

answers = set()
for water, suger in itertools.product(waters, sugers):
    if water > 0 and water + suger <= f and suger <= water // 100 * e:
        answers.add((suger + water, suger))

weight, suger = max(answers, key=lambda answer: answer[1] / answer[0])
print(weight, suger)
