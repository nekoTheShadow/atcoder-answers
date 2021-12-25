use strict;
use warnings;
use List::Util qw/reduce/;

sub f {
    my $x = shift;
    my $c = 0;
    while ($x%2 == 0) {
        $c++;
        $x /= 2;
    }
    $c;
}

chomp(my $n = <>);
my %h = map {$_, f $_} (1..$n);
my $ans = reduce {$h{$a} > $h{$b} ? $a : $b} (1..$n);
print "$ans\n";