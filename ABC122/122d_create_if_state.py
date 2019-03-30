import itertools

def swap(tpl, x, y):
    lst = list(tpl)
    lst[x], lst[y] = lst[y], lst[x]
    return tuple(lst)

for s in itertools.product('ACGT', repeat=4):
    if 'AGC' in ''.join(s) or any('AGC' in ''.join(swap(s, i, i + 1)) for i in range(len(s) - 1)):
        print("if (a == '{}' && b == '{}' && c == '{}' && d == '{}') continue;".format(*s))
