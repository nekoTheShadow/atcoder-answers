require 'set'

n, k = gets.split.map(&:to_i)
l, r = k.times.map{ gets.split.map(&:to_i) }.transpose

dp = Array.new(n, 0)
sdp = Array.new(n+1, 0)
dp[0] = 1
sdp[1] = 1

(1...n).each do |i|
  k.times do |j|
    right = [0, i - l[j]+1].max
    left = [0, i - r[j]].max

    dp[i] += sdp[right] - sdp[left]
    dp[i] %= 998244353
  end

  sdp[i+1] += sdp[i] + dp[i]
  sdp[i+1] %= 998244353
end

p dp[n-1]
