puts gets.chars.map{|c| c == '9' ? '1' : (c == '1' ? '9' : c)}.join
