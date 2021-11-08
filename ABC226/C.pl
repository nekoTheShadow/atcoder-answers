use strict;
use warnings;
use feature qw/state/;

sub gets {
    state @queue;
    unless (@queue) {
        chomp(my $line = <>);
        push @queue, split / /, $line;
    }
    shift @queue;
}

my $n = gets;
my @t;
my @a;
for (my $i=0; $i < $n; $i++) {
    push @t, gets;

    my $k = gets;
    my @nxts = ();
    for (my $j=0; $j<$k; $j++) {
        my $nxt = gets;
        push @nxts, $nxt - 1;
    }
    
    push @a, \@nxts;
}

my @stack = ($n-1);
my $ans = 0;
my %visited;
while (@stack) {
    my $cur = pop @stack;
    next if (exists $visited{$cur});

    $visited{$cur} = 1;
    $ans += $t[$cur];
    push @stack, @{$a[$cur]};
}

print $ans, "\n";