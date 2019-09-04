a, b = map(int, input().split())

# a - b - 0 -> n or p
# a - 0 - b -> zero
# 0 - a - b -> p
def main():
    if a <= 0 <= b:
        return 'Zero'
    if 1 <= a:
        return 'Positive'
    
    c = b - a + 1
    if c % 2 == 0:
        return 'Positive'
    else:
        return 'Negative'

print(main())