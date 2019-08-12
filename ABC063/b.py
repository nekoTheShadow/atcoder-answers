if __name__ == '__main__':
    s = input()
    print('yes' if len(s) == len(set(s)) else 'no')
