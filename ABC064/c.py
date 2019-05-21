#   1 - 399：灰色
#  400- 799：茶色
#  800-1199：緑色
# 1200-1599：水色
# 1600-1999：青色
# 2000-2399：黄色
# 2400-2799：橙色
# 2800-3199：赤色
# また、レートが 3200 以上になると色を自由に変えることができます。

n = int(input())
a = list(map(int, input().split()))

used_colors = set()
unknown_color = 0

for i in range(n):
    if   1  <= a[i] <=  399: used_colors.add(0)
    if  400 <= a[i] <=  799: used_colors.add(1)
    if  800 <= a[i] <= 1199: used_colors.add(2)
    if 1200 <= a[i] <= 1599: used_colors.add(3)
    if 1600 <= a[i] <= 1999: used_colors.add(4)
    if 2000 <= a[i] <= 2399: used_colors.add(5)
    if 2400 <= a[i] <= 2799: used_colors.add(6)
    if 2800 <= a[i] <= 3199: used_colors.add(7)
    if 3200 <= a[i]        : unknown_color += 1

minimum = max(1, len(used_colors))
maximum = len(used_colors) + unknown_color
print(minimum, maximum)
