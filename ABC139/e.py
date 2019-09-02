import collections

n = int(input())
a = [collections.deque(int(s) - 1 for s in input().split()) for _ in range(n)]

day = 0
players = set(range(n))
while any(v for v in a):
    nxt_players = set()
    for player in players:
        if len(a[player]) == 0 or player in nxt_players:
            continue
        
        other = a[player][0]
        if len(a[other]) == 0 or other in nxt_players:
            continue
        
        if player == a[other][0]:
            a[player].popleft()
            a[other].popleft()
            nxt_players.add(player)
            nxt_players.add(other)

    if nxt_players:
        day += 1
        players = nxt_players
    else:
        print(-1)
        exit()

print(day)