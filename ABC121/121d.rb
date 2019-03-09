a, b = gets.split.map(&:to_i)

ans = []
b.to_s(2).size.times do |x|
  d = 2 ** x
  i = a / d
  j = b / d

  if i == j
    ans << (i.odd? ? b - a + 1 : 0)
    next
  end

  sum = 0
  if i.odd? 
    y = (a / d + 1) * d
    sum += y - a
    i += 1
  end

  if j.odd?
    y = (b / d) * d - 1
    sum += b - y
    j -= 1
  end

  sum += (j - i) / 2 * d
  ans << sum
end

p ans.reverse.map{|x| x.odd? ? "1" : "0"}.join.to_i(2)