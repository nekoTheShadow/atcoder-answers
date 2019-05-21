h, w = map(int, input().split())
a = [input() for _ in range(h)]

print('#' * (w + 2))
for row in a:
    print('#' + row + '#')
print('#' * (w + 2))