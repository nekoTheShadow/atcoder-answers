n = gets.to_i
w = n.times.map{ gets.chomp }

if w.uniq.size == n && (0..(n - 2)).all?{|i| w[i][-1] == w[i + 1][0]}
  puts 'Yes'
else
  puts 'No'
end