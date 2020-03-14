n = int(input())

def dfs(s, mx):
    if len(s) == n:
        print(s)
    else:
        for c in range(ord('a'), mx+1):
            if c == mx:
                dfs(s + chr(c), mx + 1)
            else:
                dfs(s + chr(c), mx)

dfs('', ord('a'))