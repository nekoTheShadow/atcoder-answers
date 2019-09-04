import collections

s = input()
c = collections.Counter(s)

ok1 = (c['N'] == 0 and c['S'] == 0) or (c['N'] > 0 and c['S'] > 0)
ok2 = (c['E'] == 0 and c['W'] == 0) or (c['E'] > 0 and c['W'] > 0)
if ok1 and ok2:
    print('Yes')
else:
    print('No')