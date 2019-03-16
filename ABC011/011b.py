s = input()
t = ''.join(c.upper() if i == 0 else c.lower() for i, c in enumerate(s))
print(t)