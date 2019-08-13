if __name__ == '__main__':
    a, b, c = map(int, input().split())
    r = 'Yes' if a <= c <= b else 'No'
    print(r)
