if __name__ == '__main__':
    n, m = map(int, input().split())
    students, checkpoints = (tuple(tuple(map(int, input().split())) for _ in range(x)) for x in (n, m))

    for x1, y1 in students:
        t = 0
        for i in range(m):
            (x2, y2), (x3, y3) = checkpoints[t], checkpoints[i]
            if abs(x1 - x2) + abs(y1 - y2) > abs(x1 - x3) + abs(y1 - y3): t = i

        print(t + 1) 
