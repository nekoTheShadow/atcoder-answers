use strict;
use warnings;

sub check {
    my ($b, $n, $m) = @_;

    foreach my $i (0..$n-1) {
        foreach my $j (0..$m-1) {
            return 0 if ($j+1<$m and $b->[$i]->[$j]+1!=$b->[$i]->[$j+1]);
            return 0 if ($i+1<$n and $b->[$i]->[$j]+7!=$b->[$i+1]->[$j]);
            return 0 if (($b->[$i]->[$j])%7==0 and $j!=$m-1);
        }
    }
    return 1;
}

my ($n, $m) = split / /, <>;
my @b = map [split / /, <>], (1..$n);
print(check(\@b, $n, $m) ? "Yes" : "No", "\n");

