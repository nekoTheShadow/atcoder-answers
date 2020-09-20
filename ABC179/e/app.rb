n, x, m = gets.split.map(&:to_i)

a = []
h = {}
n.times do |i|
  if h.key?(x)
    j = h[x]
    ans = (0...j).sum{|e| a[e]}
    ans += (j...i).sum{|e| a[e]} * ((n-j)/(i-j))
    ans += (j...(j + (n-j) % (i-j))).sum{|e| a[e]}
    puts ans
    exit
  else
    a << x
    h[x] = i
    x = x*x%m
  end
end

puts a.sum