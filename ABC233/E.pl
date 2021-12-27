use strict;
use warnings;
use bigint;
use List::Util qw(sum);

chomp(my $x = <>);
my $s = sum split //, $x;

my $t = (10*$x-$s)/9;
print $t, "\n";