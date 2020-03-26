height, bmi = map(float, input().split())
weight = bmi * (height / 100) ** 2
print(weight)