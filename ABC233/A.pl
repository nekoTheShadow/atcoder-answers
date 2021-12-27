use strict;
use warnings;
use POSIX qw(ceil);

my ($x, $y) = split / /, <>;
printf "%d\n", $x < $y ? ceil(($y-$x)/10) : 0;