q, l = map(int, input().split())
size = 0
stack = []

for _ in range(q):
    line = input().split()
    query = line[0]
    
    if query == "Push":
        n, m = map(int, line[1:])
        stack.append([n, m])
        size += n
        if l < size:
            print("FULL")
            exit(0)
    
    if query == "Pop":
        n = int(line[1])
        if size < n:
            print("EMPTY")
            exit(0)
        
        while n > 0:
            if n < stack[-1][0]:
                stack[-1][0] -= n
                size -= n
                break

            size -= stack[-1][0]
            n -= stack[-1][0]
            stack.pop()
    
    if query == "Top":
        if size == 0:
            print("EMPTY")
            exit(0)
        print(stack[-1][1])

    if query == "Size":
        print(size)

print("SAFE")