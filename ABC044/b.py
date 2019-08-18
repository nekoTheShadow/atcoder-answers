import collections
w = input()
c = collections.Counter(w)
t = 'Yes' if all(v % 2 == 0 for v in c.values()) else 'No'
print(t)