c = 4.times.flat_map{ gets.chomp.split }
c.reverse.each_slice(4).each{|row| puts row.join(' ')}