n = gets.to_i
t = n.times.map{ gets.to_i }
p t.reduce{|x, y| x.lcm(y)}