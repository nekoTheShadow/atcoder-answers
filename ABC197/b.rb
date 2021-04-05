h, w, x, y = gets.split.map(&:to_i)
s = h.times.map{ gets.chomp }

x -= 1
y -= 1

ans = 1

a = x-1
while a >= 0
  break if s[a][y] == '#' 
  ans += 1 
  a -= 1
end

a = x+1
while a < h
  break if s[a][y] == '#' 
  ans += 1 
  a += 1
end

b = y-1
while b >= 0
  break if s[x][b] == '#' 
  ans += 1 
  b -= 1
end

b = y+1
while b < w
  break if s[x][b] == '#' 
  ans += 1 
  b += 1
end

puts ans