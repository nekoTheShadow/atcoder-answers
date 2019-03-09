n = int(input())

t = (n // 11) * 2
mod = n % 11
if mod == 0:
    pass
elif 1 <= mod <= 6:
    t += 1
else:
    t += 2

print(t)