# n = 30 で振り出しに戻る。


a = []
for n in range(30):
    c = list(map(str, range(1, 7)))
    for i in range(n):
        c[i % 5], c[i % 5 + 1] = c[i % 5 + 1], c[i % 5]
    a.append(''.join(c))

N = int(input())
print(a[N % 30])



# 確認用のコード:

# import itertools

# d = {}
# for n in itertools.count(0):
#     c = list(map(str, range(1, 7)))
#     for i in range(n):
#         c[i % 5], c[i % 5 + 1] = c[i % 5 + 1], c[i % 5]

#     k = ''.join(c)
#     if k in d:
#         break
#     else:
#         d[k] = n
    
