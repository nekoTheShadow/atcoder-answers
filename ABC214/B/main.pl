#!/usr/bin/perl

use strict;
use warnings;

my ($s, $t) = split / /, <>;
my $ans = 0;
foreach my $a (0..$s) {
    foreach my $b (0..$s) {
        foreach my $c (0..$s) {
            $ans++ if $a+$b+$c<=$s && $a*$b*$c<=$t;
        }
    }
}
print "$ans\n";