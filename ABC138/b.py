import fractions

n = int(input())
a = list(map(int, input().split()))

print(float(1 / sum(fractions.Fraction(1, x) for x in a)))
