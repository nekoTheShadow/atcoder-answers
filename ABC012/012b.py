n = int(input())
h = (n // 60) // 60
m = (n // 60) % 60
s = n % 60
print('{:0>2}:{:0>2}:{:0>2}'.format(h, m, s))