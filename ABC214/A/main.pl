#!/usr/bin/perl

use strict;
use warnings;

my $n = <>;
if (1<=$n && $n<=125) {
    print "4\n";
} elsif (126<=$n && $n<=211) {
    print "6\n";
} else {
    print "8\n";
}