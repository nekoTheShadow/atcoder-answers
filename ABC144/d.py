import math

a, b, x = map(float, input().split())

if a * a * b == x:
    print(0)
    exit(0)

if x * 2 <= a * a * b:
    P = (2 * x) / (a * b)
    Q = b
else:
    P = a
    Q = (2 * (a * a * b - x)) / (a * a)

R = math.sqrt(P * P + Q * Q)
cosp = (Q * Q + R * R - P * P) / (2 * Q * R)
print(90 - math.degrees(math.acos(cosp)))