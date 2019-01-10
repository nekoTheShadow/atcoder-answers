require 'date'

a, b = gets.split.map(&:to_i)
d = Date.new(2018, 1, 1)
ans = 0
while d <= Date.new(2018, a, b)
  ans += 1 if d.month == d.day
  d += 1
end

puts ans