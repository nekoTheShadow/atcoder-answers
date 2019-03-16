s = gets.chomp
i = 0
while i < s.size - 1
  if  s[i] == 'W' && s[i + 1] == 'A'
    s[i] = 'A'
    s[i + 1] = 'C'
    i = [0, i - 1].max
  else
    i += 1
  end
end
puts s