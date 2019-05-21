def solve(h, w):
    ans = float('inf')
    for x in range(1, h):
        a = x * w
        b = ((h - x) // 2) * w
        c = ((h - x) - (h - x) // 2) * w

        minimum = min(a, min(b, c))
        maximum = max(a, max(b, c))
        ans = min(abs(maximum - minimum), ans)

        b = (h - x) * (w // 2)
        c = (h - x) * (w - w // 2)
        minimum = min(a, min(b, c))
        maximum = max(a, max(b, c))
        ans = min(abs(maximum - minimum), ans)
    
    return ans


h, w = map(int, input().split())
print(min(solve(h, w), solve(w, h)))