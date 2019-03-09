a, b, c = gets.split.map(&:to_i)
count = 0
until a == b && b == c
  count += 1 
  a, b, c = [a, b, c].sort
  if a + 2 <= c
    a += 2
  else
    a += 1
    b += 1
  end
end
puts count