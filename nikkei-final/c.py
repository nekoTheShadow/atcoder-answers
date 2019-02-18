def f(count, pivot):
    total = 0
    for i in range(len(count)):
        total += count[i]
        if pivot < total:
            break
    return i

if __name__ == '__main__':
    h, w, k = map(int, input().split())
    count1 = [w] * h
    count2 = [h] * w

    for _ in range(k):
        r, c = map(int, input().split())
        count1[r - 1] -= 1
        count2[c - 1] -= 1

    pivot = (h * w - k) // 2
    i1 = f(count1, pivot)
    i2 = f(count2, pivot)

    ans1 = sum(abs(i1 - i) * count1[i] for i in range(h))
    ans2 = sum(abs(i2 - i) * count2[i] for i in range(w))
    print(ans1 + ans2)