n = input()
ans = 'Yes' if int(n) % sum(map(int, n)) == 0 else 'No'
print(ans)