def main(s):
    for x in range(len(s)):
        for y in range(x, len(s)):
            if s[0:x] + s[y:] == 'keyence':
                return 'YES'
    return 'NO'

print(main(input()))
                    