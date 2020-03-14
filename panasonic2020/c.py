import decimal

a, b, c = map(int, input().split())
if decimal.Decimal(a).sqrt() + decimal.Decimal(b).sqrt() < decimal.Decimal(c).sqrt():
    print('Yes')
else:
    print('No')
