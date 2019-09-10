n, a, b, c, d = (int(e) - 1 for e in input().split())
s = input()

if c < d:
    ok1 = not any(s[i] == s[i + 1] == '#' for i in range(a, c))
    ok2 = not any(s[i] == s[i + 1] == '#' for i in range(b, d))
    print('Yes' if ok1 and ok2 else 'No')
else:
    ok1 = not any(s[i] == s[i + 1] == '#' for i in range(a, c))
    ok2 = not any(s[i] == s[i + 1] == '#' for i in range(b, d))
    ok3 = any(s[i - 1] == s[i] == s[i + 1] == '.' for i in range(b, d + 1) if 0 < i and i < n - 1)
    print('Yes' if ok1 and ok2 and ok3 else 'No')


