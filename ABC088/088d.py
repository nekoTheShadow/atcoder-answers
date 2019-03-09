def main():
    h, w = map(int, input().split())
    matrix = [list(input()) for _ in range(h)]

    stack = [(0, 0)]
    diffs = ((0, 1), (1, 0), (0, -1), (-1, 0))
    distance = [[float('inf')] * w for _ in range(h)]
    distance[0][0] = 1
    while stack:
        x, y = stack.pop()
        for dx, dy in diffs:
            nx, ny = x + dx, y + dy
            if 0 <= nx < h and 0 <= ny < w \
                           and matrix[nx][ny] == '.' \
                           and distance[x][y] + 1 < distance[nx][ny]:
                distance[nx][ny] = distance[x][y] + 1
                stack.append((nx, ny))

    if distance[h - 1][w - 1] == float('inf'):
        return -1
    else:
        white = sum(row.count('.') for row in matrix)
        return white - distance[h - 1][w - 1]

if __name__ == '__main__':
    print(main())