use v5.10;
use strict;
use warnings;

my $n = <>;
my @answer;
while ($n > 0) {
    if ($n%2 == 0) {
        $n /= 2;
        push @answer, "B";
    } else {
        $n--;
        push @answer, "A";
    }
}

say join "", reverse @answer;