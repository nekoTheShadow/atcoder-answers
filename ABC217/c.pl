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

my $n = input;
my @p;
my @q;
push @p, input foreach (1..$n);
foreach my $i (0..$n-1) {
    $q[$p[$i]-1] = $i+1;
}
output @q
