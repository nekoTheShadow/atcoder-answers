require 'prime'

n = gets.to_i
puts Prime.each(55555).select{|x| x % 5 == 1}.take(n).join(' ')