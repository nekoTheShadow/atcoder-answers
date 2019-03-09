s = gets.chomp
t = gets.chomp

words = [s]
(s.size - 1).times do
  word = words[-1]
  words << word[-1] + word[0...-1]
end

puts words.include?(t) ? 'Yes' : 'No'