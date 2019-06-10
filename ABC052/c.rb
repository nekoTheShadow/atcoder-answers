require "prime"

n = gets.to_i

c = Hash.new(0)
(1..n).each do |x|
  x.prime_division.each do |(factor, e)|
    c[factor] += e
  end
end

ans = 1
c.each_value do |e|
  ans *= e + 1
  ans %= 10 ** 9 + 7
end

p ans