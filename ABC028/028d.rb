n, k = gets.split.map(&:to_i)
t = 6 * (k - 1) * (n - k) # ABC
t += 3 * (n - k)          # BBC
t += 1                    # BBB
t += 3 * (k - 1)          # ABB
puts t.quo(n ** 3).to_f