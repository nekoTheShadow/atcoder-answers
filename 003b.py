import itertools

def main():
    s = input()
    t = input()
    for c1, c2 in zip(s, t):
        if c1 == c2:
            continue
        else:
            if c1 == '@' and c2 in 'atcoder':
                continue
            elif c2 == '@' and c1 in 'atcoder':
                continue
            else:
                return 'You will lose'
    return 'You can win'

print(main())
    

