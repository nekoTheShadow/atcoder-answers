numbers = gets.split.map(&:to_i)
p numbers.combination(3).map{|a, b, c| a + b + c}.uniq.sort.reverse[2]