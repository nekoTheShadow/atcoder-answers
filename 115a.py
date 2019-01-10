d = int(input())
answer = 'Christmas ' + ' '.join('Eve' for _ in range(25 - d))
print(answer)