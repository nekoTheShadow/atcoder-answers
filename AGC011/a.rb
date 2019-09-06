n, c, k = gets.split.map(&:to_i)
times = n.times.map{gets.to_i}.sort


bus = i = 0
while i < n
  pivot = times[i]

  cnt = 0
  while i < n && times[i] <= pivot + k
    cnt += 1
    i += 1
  end

  i -= cnt - c if cnt > c
  bus += 1
end

puts bus
