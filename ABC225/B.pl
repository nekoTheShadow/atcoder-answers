use strict;
use warnings;

my $n = <>;
my %c;
foreach (1..$n-1) {
    my ($a, $b) = map $_-1, split / /, <>;
    $c{$a}++;
    $c{$b}++;
}

my %d;
$d{$_}++ foreach(values %c);

print(($d{1}==$n-1 and $d{$n-1}==1) ? "Yes" : "No", "\n");