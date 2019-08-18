if __name__ == '__main__':
    a, b = map(int, input().split())
    x = a + b
    print(x if x < 10 else "error")
