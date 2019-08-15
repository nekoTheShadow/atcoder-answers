if __name__ == '__main__':
    a, b = input().split()

    print('D' if (a == 'H') ^ (b == 'H') else 'H')
