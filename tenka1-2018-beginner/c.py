n = int(input())
a = [int(input()) for _ in range(n)]
a.sort()
m = n // 2 - 1
if n % 2 == 0:
    ans = sum(a[:m]) * -2 - a[m] + a[m+1] + sum(a[m+2:]) * 2
else:
    ans1 = sum(a[:m]) * -2 - (a[m] + a[m+1]) + sum(a[m+2:]) * 2
    ans2 = sum(a[:m+1]) * -2 + (a[m+1] + a[m+2]) + sum(a[m+3:]) * 2
    ans = max(ans1, ans2)
    
print(ans)
    
