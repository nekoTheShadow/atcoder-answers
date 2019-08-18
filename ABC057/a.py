if __name__ == '__main__':
    a, b = map(int, input().split())
    diff = (a + b) % 24
    print(diff)
