use v5.10;
use strict;
use warnings;

sub input() {
    state @que;
    if (@que == 0) {
        chomp(my $line = <>);
        push @que, split / /, $line;
    }
    return shift @que;
}

my ($x, $y) = split /\./, input;
if (0 <= $y && $y <= 2) {
    say "$x-";
} elsif (3 <= $y && $y <= 6) {
    say "$x";
} elsif (7 <= $y && $y <= 9) {
    say "$x+";
}