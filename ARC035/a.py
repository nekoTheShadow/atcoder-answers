s = input()
t = ''.join(reversed(s))

def f():
    for i in range(len(s)):
        if s[i] != '*' and t[i] != '*' and s[i] != t[i]:
            return 'NO'
    return 'YES'

print(f())