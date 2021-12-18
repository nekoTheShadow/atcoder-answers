use strict;
use warnings;

chomp(my $n = <>);
my %d;
foreach (1..$n) {
    chomp(my $s = <>);
    $d{$s}++;
}

my $max_key;
my $max_val = 0;
while (my ($key, $val) = each %d) {
    if ($max_val < $val) {
        $max_key = $key;
        $max_val = $val;
    }
}

print "$max_key\n";