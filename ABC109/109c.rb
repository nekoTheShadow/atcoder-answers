N, X = gets.split.map(&:to_i)
x = gets.split.map{|s| (s.to_i - X).abs}

d = x[0]
(1...N).each do |i|
  d = d.gcd(x[i])
end
puts d