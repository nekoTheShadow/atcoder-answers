a, b, c, d, e, f, g, h, i = 3.times.flat_map{ gets.split.map(&:to_i) }
puts a - b == d - e && d - e == g - h && b - c == e - f && e - f == h - i ? 'Yes' : 'No'