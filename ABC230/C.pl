use strict;
use warnings;

my ($n, $a, $b) = split / /, <>;
my ($p, $q, $r, $s) = split / /, <>;

my $h = $q - $p + 1;
my $w = $s - $r + 1;

my @ans;
foreach my $i ($p..$q) {
    push @ans, join "", map {($i-$_ == $a-$b or $i+$_ == $a+$b) ? "#" : "."} ($r..$s);
}
print "$_\n" foreach(@ans);