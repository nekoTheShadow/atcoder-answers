s = input()
k = int(input())

if all(c == '1' for c in s[0:k]):
    print('1')
else:
    print(next(c for c in s if c != '1'))