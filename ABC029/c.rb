n = gets.to_i
%w(a b c).repeated_permutation(n).map(&:join).sort.each{|v| puts v}