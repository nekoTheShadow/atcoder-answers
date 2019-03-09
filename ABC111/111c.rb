n = gets.to_i
v = gets.split.map(&:to_i)

even = Hash.new(0)
odd = Hash.new(0)
(0...n).step(2){|x| even[v[x]] += 1}
(1...n).step(2){|x| odd[v[x]] += 1}

e1, e2 = even.keys.sort_by{|x| even[x]}.reverse[0..1]
o1, o2 = odd.keys.sort_by{|x| odd[x]}.reverse[0..1]

answers = []
[e1, e2].product([o1, o2]) do |e, o|
  next if e == o
  answers << (n / 2 - even[e]) + (n / 2 - odd[o])
end

puts answers.min