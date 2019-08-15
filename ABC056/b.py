if __name__ == '__main__':
    w, a, b = map(int, input().split())
    
    if b + w < a: 
        print(a - b - w)
    elif a <= b + w <= a + w or a <= b <= a + w: 
        print(0)
    else: 
        print(b - a - w)

