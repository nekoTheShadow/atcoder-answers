use strict;
use warnings;
use utf8;

my $x = <>;

if ($x < 40) {
    print 40-$x, "\n";
} elsif ($x < 70) {
    print 70-$x, "\n";
} elsif ($x < 90) {
    print 90-$x, "\n";
} else {
    print "expert\n";
}