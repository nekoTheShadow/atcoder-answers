use strict;
use warnings;

my ($l, $r) = split / /, <>;
chomp(my $s = <>);

my $a = substr $s, 0, $l-1;
my $b = reverse substr $s, $l-1, $r-$l+1;
my $c = substr $s, $r;
print "$a$b$c\n";