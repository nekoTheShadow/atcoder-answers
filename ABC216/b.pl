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

my $n = input();

my @s;
my @t;
foreach(1..$n) {
    push @s, input;
    push @t, input;
}

for (my $i = 0; $i < $n; $i++) {
    for (my $j = $i + 1; $j < $n; $j++) {
        if ($s[$i] eq $s[$j] && $t[$i] eq $t[$j]) {
            say "Yes";
            exit;
        }
    }
}
say "No";