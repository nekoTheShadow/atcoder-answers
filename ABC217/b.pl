use strict;
use warnings;
use feature qw/state/;

sub output {
    print "@_\n";
}

sub input {
    state @que;
    if (@que == 0) {
        chomp(my $line = <>);
        push @que, split / /, $line;
    }
    return shift @que;
}

my @s;
push @s, input;
push @s, input;
push @s, input;

foreach my $v (qw(ABC ARC AGC AHC)) {
    output $v unless grep {$_ eq $v} @s;
}