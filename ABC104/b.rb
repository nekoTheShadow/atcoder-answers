e def solve(s)
  return false unless s[0] == 'A'

  t = s[2..-2]
  return false unless t.count('C') == 1

  x = t.index('C') + 2
  (0...s.size).each do |i|
    next if i == 0 || i == x
    return false unless s[i] == s[i].downcase
  end

  true
end

s = gets.chomp
puts solve(s) ? 'AC' : 'WA'
