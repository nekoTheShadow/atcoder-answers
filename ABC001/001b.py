m = int(input())
n = m / 1000

if n < 0.1:
    print('00')
elif 0.1 <= n <= 5:
    print(str(int(n * 10)).zfill(2))
elif 6 <= n <= 30:
    print(int(n) + 50)
elif 35 <= n <= 70:
    print((int(n) - 30) // 5 + 80)
else: # 70 <= n
    print(89)