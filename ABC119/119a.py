import datetime

s = input()
year, month, day = map(int, s.split('/'))
d = datetime.date(year, month, day)
if d <= datetime.date(2019, 4, 30):
    print('Heisei')
else:
    print('TBD')