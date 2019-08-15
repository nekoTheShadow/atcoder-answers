import string, collections

if __name__ == '__main__':
    n = int(input())

    tables = []
    for _ in range(n):
        line = input()

        table = collections.defaultdict(int)
        for ch in line: table[ch] += 1

        tables.append(table)

    answers = []
    for ch in string.ascii_lowercase:
        x = min(table[ch] for table in tables)
        answers.extend(ch for _ in range(x))

    print(''.join(answers))
