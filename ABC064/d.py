n = int(input())
s = input()

left = 0
stack = []
for i in range(n):
    if s[i] == '(':
        stack.append('(')
    else:
        if len(stack) == 0:
            left += 1
        else:
            stack.pop()
right = len(stack)

t = ('(' * left) + s + (')' * right)
print(t)