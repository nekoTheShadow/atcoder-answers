n = int(input())
s = input().split()

ans = 'Three' if len(set(s)) == 3 else 'Four'
print(ans)