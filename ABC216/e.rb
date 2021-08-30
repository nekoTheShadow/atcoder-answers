n, k = gets.split.map(&:to_i)
a = gets.split.map(&:to_i).sort.reverse
a << 0

ans = 0
i = 0
f = false
while i < n
  tmp = (i + 1) * (a[i] - a[i+1])
  if k - tmp < 0
    f = true
    break
  end
  ans += (i+1) * (a[i]*(a[i]+1) - (a[i+1])*(a[i+1]+1)) / 2
  k -= tmp 
  i += 1
end

if f
  div = k / (i+1)
  mod = k % (i+1)
  tmp = a[i]

  ans += (i+1) * (tmp*(tmp+1) - (tmp-div)*(tmp-div+1))/2
  ans += mod * (tmp-div)
end

p ans