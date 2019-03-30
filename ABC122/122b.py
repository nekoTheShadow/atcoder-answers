s = input()
answer = 0
for x in range(len(s)):
    for y in range(x + 1, len(s) + 1):
        t = s[x:y]
        if all(c in 'ACGT' for c in t):
            answer = max(answer, len(t))

print(answer)
