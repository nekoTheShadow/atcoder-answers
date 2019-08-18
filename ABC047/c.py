s = input()
c = sum(1 for i in range(len(s) - 1) if s[i] != s[i + 1])
print(c)