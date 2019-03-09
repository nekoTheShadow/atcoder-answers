def solve(s):
    stack = [s]
    while stack:
        word = stack.pop()
        if len(word) == 0:
            return True
        for prefix in ('dreamer', 'dream', 'eraser', 'erase'):
            if word.startswith(prefix):
                stack.append(word[len(prefix):])

print('YES' if solve(input()) else 'NO')