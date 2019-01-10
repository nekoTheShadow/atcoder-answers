h, w = gets.split.map(&:to_i)
a = h.times.map{ gets.split.map(&:to_i) }

rows = []
[*0...h].product([*0...w]) do |x1, y1|
  next if a[x1][y1].even? || (x1 == h - 1 && y1 == w - 1)
  x2, y2 = (y1 == w - 1) ? [x1 + 1, y1] : [x1, y1 + 1]
  a[x1][y1] -= 1
  a[x2][y2] += 1
  rows << [x1, y1, x2, y2]
end

puts rows.size
rows.each do |row|
  puts row.map(&:succ).join(' ')
end