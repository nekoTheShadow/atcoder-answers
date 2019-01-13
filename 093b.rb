a, b, k = gets.split.map(&:to_i)
[*a.upto([a + k - 1, b].min), *b.downto([b - k + 1, a].max)].uniq.sort.each{|x| puts x}