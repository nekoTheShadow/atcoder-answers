n = gets.to_i
vals = gets.split.map(&:to_i)

s = vals.max
t = vals.min_by{|val| val == s ? Float::INFINITY : (val - s / 2).abs}

puts "#{s} #{t}"