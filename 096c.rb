def main()
  h, w = gets.split.map(&:to_i)
  matrix = h.times.map{ gets.chomp.chars }

  diffs = [[1, 0], [-1, 0], [0, 1], [0, -1]]
  [*0...h].product([*0...w]) do |x, y|
    next if matrix[x][y] == '.'
    return 'No' if  diffs.map{|(dx, dy)| [x + dx, y + dy]} \
                        .select{|(nx, ny)| 0 <= nx && nx < h && 0 <= ny && ny < w} \
                        .all?{|(nx, ny)| matrix[nx][ny] == '.'}
  end
  'Yes'
end

puts main()