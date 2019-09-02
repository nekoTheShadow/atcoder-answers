def f(n, a, x, y)
  return f(n, a, y, x) if x > y
  s = t = 0
  (x..y).each do |z|
    if (z - x) % 2 == 0
      s += a[z]
    else
      t += a[z]
    end
  end
  return [s, t]
end

n = gets.to_i
a = gets.split.map(&:to_i)

answers = []
n.times do |x|
  slist, tlist = [], []
  n.times do |y|
    next if x == y
    s, t = f(n, a, x, y)
    slist << s
    tlist << t
  end
  answers << slist[tlist.index(tlist.max)]
end

puts answers.max
