n = gets.to_i
puts n.times.map{ gets.chomp }.tally.invert.max_by(&:first).last