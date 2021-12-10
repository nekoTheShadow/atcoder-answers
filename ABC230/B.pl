use strict;
use warnings;

chomp(my $s = <>);
my $t = "oxx" x 100;

print index($t, $s) != -1 ? "Yes\n" : "No\n";