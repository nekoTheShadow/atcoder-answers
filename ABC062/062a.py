x, y = map(int, input().split())
group = [0] * 13
group[ 0] = 1
group[ 2] = 2
group[ 4] = 3
group[ 6] = 3
group[ 9] = 3
group[11] = 3

print('Yes' if group[x] == group[y] else 'No')
