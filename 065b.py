def main():
    n = int(input())
    nxts = [int(input()) - 1 for _ in range(n)]

    history = set()
    current = 0
    count = 0
    while current != 1:
        if current in history:
            return -1
        history.add(current)
        count += 1
        current = nxts[current]

    return count

if __name__ == '__main__':
    print(main())