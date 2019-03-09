s = input()
ans = min(abs(753 - int(s[x:x+3])) for x in range(0, len(s) - 2))
print(ans)