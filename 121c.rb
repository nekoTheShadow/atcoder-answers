n, m = gets.split.map(&:to_i)
alist = [] # yen
blist = [] # 本数
n.times do |i|
  alist[i], blist[i] = gets.split.map(&:to_i)
end

ans = 0
[*0...n].sort_by{|i| alist[i]}.each do |i|
  a = alist[i]
  b = blist[i]

  if m <= b # 残りの本数が少ない場合
    ans += m * a
    break
  else
    ans += a * b
    m -= b
  end
end

p ans