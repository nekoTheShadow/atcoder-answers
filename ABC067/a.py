a, b = map(int, input().split())
ans = 'Possible' if any(x % 3 == 0for x in (a, b, a + b)) else 'Impossible'
print(ans)