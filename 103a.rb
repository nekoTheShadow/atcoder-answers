puts gets.split.map(&:to_i).permutation.map{|(a, b, c)| (a - b).abs + (b - c).abs}.min