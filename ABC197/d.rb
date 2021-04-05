require 'matrix'

n = gets.to_f
x0, y0 = gets.split.map(&:to_f)
xn2, yn2 = gets.split.map(&:to_f)

r = 2*Math::PI/n
a = Matrix[[Math.cos(r), -Math.sin(r)], [Math.sin(r), Math.cos(r)]]
b = Matrix[[x0], [y0]] - Matrix[[(x0+xn2)/2], [(y0+yn2)/2]]
c = Matrix[[(x0+xn2)/2], [(y0+yn2)/2]]

ans = a*b+c
puts "#{ans[0, 0]} #{ans[1, 0]}"