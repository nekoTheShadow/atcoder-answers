N, M, X, Y = gets.split.map(&:to_i)
x = gets.split.map(&:to_i)
y = gets.split.map(&:to_i)

z1 = x.max + 1
z2 = y.min

puts (z1..z2).any?{|z| X < z && z <= Y} ? "No War" : "War"
