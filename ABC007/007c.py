r, c = map(int, input().split())
sx, sy = map(lambda s : int(s) - 1, input().split())
gx, gy = map(lambda s : int(s) - 1, input().split())
board = [[-1 if c == '#' else float('inf') for c in input()] for _ in range(r)]

board[sx][sy] = 0
stack = [(sx, sy)]
while stack:
    x, y = stack.pop()
    for dx, dy in ((0, 1), (0, -1), (1, 0), (-1, 0)):
        nx, ny = x + dx, y + dy
        if 0 <= nx < r and 0 <= ny < c and board[x][y] + 1 < board[nx][ny]:
            board[nx][ny] = board[x][y] + 1
            stack.append((nx, ny))

print(board[gx][gy])