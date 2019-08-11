s = int(input())
st = set()
while not s in st:
    st.add(s)
    if s % 2 == 0:
        s //= 2
    else:
        s = 3 * s + 1
print(len(st) + 1)