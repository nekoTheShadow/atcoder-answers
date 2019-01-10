s = gets.chomp
k = gets.to_i

words = []
len = s.size
(0...len).each do |x|
  max = [x + k, len].min
  (x...max).each do |y|
    words << s[x..y]
  end
end

puts words.uniq.sort[k - 1]