use strict;
use warnings;
use Data::Dumper;

sub gets {
    chomp(my $line = <>);
    split / /, $line;
}

my ($n, $m) = gets;
my %g;
foreach (1..$m) {
    my ($a, $b) = map $_-1, gets;
    push @{$g{$a}}, $b;
    push @{$g{$b}}, $a;
}

my @stack = ([0, 0]);
my %visited;
while (@stack) {
    my ($cur, $count) = @{pop @stack};
    
    next if ($count > 2 or exists($visited{$cur}));
    $visited{$cur} = $count;
    push @stack, [$_, $count+1] foreach (@{$g{$cur}});
}

print exists($visited{$n-1}) ? "POSSIBLE\n" : "IMPOSSIBLE\n";
