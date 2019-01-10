x = gets.to_i
nums = [1]
(2...x).each do |b|
  p = 2
  while (num = b ** p) <= x
    nums << num
    p += 1
  end
end

p nums.sort.last