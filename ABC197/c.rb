n = gets.to_i
a = gets.split.map(&:to_i)

ans = Float::INFINITY
[true, false].repeated_permutation(n-1) do |bit|
  sum = 0
  cur = a[0]
  (n-1).times do |i|
    if bit[i]
      sum ^= cur
      cur = 0
    end
    cur |= a[i+1]
  end
  ans = [ans, cur^sum].min
end
p ans