use strict;
use warnings;

sub gets {
    chomp(my $s = <>);
    split / /,$s;
}

my ($n, $k) = gets;
my @a = gets;

my @b = (0);
for my $i (0..$n-1) {
    $b[$i+1] = $b[$i] + $a[$i];
}

my %h;
$h{$_}++ foreach(@b);

my $ans = 0;
foreach my $l (0..$n-1) {
    $h{$b[$l]}--;
    $ans += $h{$b[$l]+$k}
}
print "$ans\n";