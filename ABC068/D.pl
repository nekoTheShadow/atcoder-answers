use strict;
use warnings;
use bigint;

chomp(my $k = <>);
my $n = 50;
my $q = $k / $n;
my $r = $k % $n;

my @ans = (($n+$q) x $r, ($n-$r+$q-1) x ($n-$r));
print "50\n@ans\n";
