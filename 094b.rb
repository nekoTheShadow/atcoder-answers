n, m, x = gets.split.map(&:to_i)
as = gets.split.map(&:to_i)

c = as.count{|a| a < x}
puts [c, as.size - c].min