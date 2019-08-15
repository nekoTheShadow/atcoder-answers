if __name__ == '__main__':
    odds, evens = (input() for _ in range(2))

    answer = []
    for odd, even in zip(odds, evens): answer.extend((odd, even))

    if len(odds) != len(evens):
        remains = odds if len(odds) > len(evens) else evens
        answer.append(remains[-1])

    print(''.join(answer))
