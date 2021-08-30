n, m = gets.split.map(&:to_i)
a = []
m.times do
  k = gets.to_i
  a << gets.split.map{|v| v.to_i-1}
end

c = Array.new(n){[]}
m.times do |i|
  c[a[i][0]] << i
end
q = n.times.filter{|i| c[i].size==2}

until q.empty?
  i = q.shift
  x1, x2 = c[i]

  a[x1].shift
  a[x2].shift

  unless a[x1].empty?
    c[a[x1][0]] << x1
    q << a[x1][0] if c[a[x1][0]].size == 2
  end
  unless a[x2].empty?
    c[a[x2][0]] << x2
    q << a[x2][0] if c[a[x2][0]].size == 2
  end
end

puts a.all?(&:empty?) ? "Yes" : "No"
