n = gets.to_i
d = n.times.map{ gets.split.map(&:to_i) }

if (n-2).times.any?{|i| d[i][0]==d[i][1] && d[i+1][0]==d[i+1][1] && d[i+2][0]==d[i+2][1]}
  puts "Yes"
else
  puts "No"
end