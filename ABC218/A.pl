use strict;
use warnings;

my $n = <STDIN>;
my $s = <STDIN>;
print(substr($s, $n-1, 1) eq "o" ? "Yes\n" : "No\n");