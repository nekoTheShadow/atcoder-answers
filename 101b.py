n = input()
s = sum(map(int, n))
ans = 'Yes' if int(n) % s == 0 else 'No'
print(ans)