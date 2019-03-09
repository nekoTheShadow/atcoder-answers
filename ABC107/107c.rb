n, k = gets.split.map(&:to_i)
x = gets.split.map(&:to_i)

puts (0..(n - k)).flat_map{|i|
  j = i + k - 1
  [x[i].abs + (x[i] - x[j]).abs, x[j].abs + (x[j] - x[i]).abs]
}.min
