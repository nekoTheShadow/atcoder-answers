use strict;
use warnings;

chomp(my $s = <>);
my %d = map {$_, 1} split //, $s;
print "1\n" if (%d==1);
print "3\n" if (%d==2);
print "6\n" if (%d==3);