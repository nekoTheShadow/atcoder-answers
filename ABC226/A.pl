use strict;
use warnings;

my $x = <>;
if ($x =~ /(\d*)\.(\d)/) {
    print $2<=4 ? $1 : $1+1, "\n";
}