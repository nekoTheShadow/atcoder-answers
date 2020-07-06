awk -v x="AC WA TLE RE" '
BEGIN {
  split(x,y)
  for(i=1;i<=4;i++) a[y[i]]=0
}
NR!=1 {
  a[$0]++
}
END{
  for(i=1;i<=4;i++) print y[i],"x",a[y[i]]
}'