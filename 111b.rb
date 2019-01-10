n = gets.to_i
n += 1 until n.to_s.chars.uniq.size == 1
puts n
