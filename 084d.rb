require 'prime'

q = gets.to_i
l, r = [], []
q.times do |i|
  l[i], r[i] = gets.split.map(&:to_i)
end

s = [0]
(1..r.max).each do |n|
  s << ((n.prime? && ((n + 1) / 2).prime?) ? s[-1] + 1 : s[-1])
end

q.times do |i|
  puts s[r[i]] - s[l[i] - 1]
end