import copy

def f(t, i):
    if len(t) == i:
        return [t]

    result = []
    if t[i] == 'A':
        t1 = copy.deepcopy(t)
        t2 = copy.deepcopy(t)
        t1[i] = ''
        result.extend(f(t1, i + 1))
        result.extend(f(t2, i + 1))
    else:
        result.extend(f(t, i + 1))
    return result

st = set()
tokens = f(list('AKIHABARA'), 0)
for token in tokens:
    st.add(''.join(token))

s = input()
if s in st:
    print('YES')
else:
    print('NO')
