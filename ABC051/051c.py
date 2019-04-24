sx, sy, tx, ty = map(int, input().split())
dx, dy = tx - sx, ty - sy

ans = "" 
ans += 'U' * dy + 'R' * dx 
ans += 'D' * dy + 'L' * dx
ans += 'L'
ans += 'U' * (dy + 1) + 'R' * (dx + 1)
ans += 'D'
ans += 'R'
ans += 'D' * (dy + 1) + 'L' * (dx + 1)
ans += 'U'

print(ans)