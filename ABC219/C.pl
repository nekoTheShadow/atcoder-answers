use strict;
use warnings;
use utf8;

sub gets {
    chomp(my $line = <>); 
    $line;
}

my @x = split //, gets;
my $n = gets;
my @s;
for (my $i = 0; $i < $n; $i++) {
    push @s, gets;
}

my @y = ('a'..'z');
my %dict;
for (my $i = 0; $i < 26; $i++) {
    $dict{$x[$i]} = $y[$i];
}

my @t;
foreach my $p (@s) {
    my $q = join '', map {$dict{$_}} split //, $p;
    push @t, [$q, $p];
}

foreach my $v (sort {$a->[0] cmp $b->[0]} @t) {
    print $v->[1], "\n";
}
