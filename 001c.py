import decimal

deg, dis = map(int, input().split())

dirs = ["NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"]
dir = 'N'
for i in range(len(dirs)):
    lo = decimal.Decimal('11.25') + decimal.Decimal('22.5') * decimal.Decimal(i)
    if lo <= decimal.Decimal(deg) / decimal.Decimal(10) < lo + decimal.Decimal('22.5'):
        dir = dirs[i]
        break

bounds = [
    (decimal.Decimal('   0'), decimal.Decimal(' 0.2')),
    (decimal.Decimal(' 0.3'), decimal.Decimal(' 1.5')),
    (decimal.Decimal(' 1.6'), decimal.Decimal(' 3.3')),
    (decimal.Decimal(' 3.4'), decimal.Decimal(' 5.4')),
    (decimal.Decimal(' 5.5'), decimal.Decimal(' 7.9')),
    (decimal.Decimal(' 8.0'), decimal.Decimal('10.7')),
    (decimal.Decimal('10.8'), decimal.Decimal('13.8')),
    (decimal.Decimal('13.9'), decimal.Decimal('17.1')),
    (decimal.Decimal('17.2'), decimal.Decimal('20.7')),
    (decimal.Decimal('20.8'), decimal.Decimal('24.4')),
    (decimal.Decimal('24.5'), decimal.Decimal('28.4')),
    (decimal.Decimal('28.5'), decimal.Decimal('32.6')),
    (decimal.Decimal('32.7'), float('inf'))
]

for i in range(len(bounds)):
    lo, hi = bounds[i]
    speed = (decimal.Decimal(dis) / decimal.Decimal(60)).quantize(decimal.Decimal('1.0'), rounding=decimal.ROUND_HALF_UP)
    if lo <= speed <= hi:
        w = i

if w == 0:
    print('C 0')
else:
    print(dir, w)
