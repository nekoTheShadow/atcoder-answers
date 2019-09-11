import string


s = input()
t = set(s)
for ch in sorted(string.ascii_lowercase):
    if not ch in t:
        print(ch)
        exit(0)

print('None')