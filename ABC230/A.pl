use strict;
use warnings;

chomp(my $n = <>);
$n++ if ($n >= 42);
printf "AGC%03d\n", $n;