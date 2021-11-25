use strict;
use warnings;

my ($n, $q) = split / /, <>;

my @a = (-1) x $n; # 自分の前
my @b = (-1) x $n; # 自分の後

while ($q--) {
    my @query = split / /, <>;
    if ($query[0] == 1) {
        my ($x, $y) = map $_-1, @query[1,2];
        $a[$y] = $x;
        $b[$x] = $y;
    }
    
    if ($query[0] == 2) {
        my ($x, $y) = map $_-1, @query[1,2];
        $a[$y] = -1;
        $b[$x] = -1;
    }

    if ($query[0] == 3) {
        my $x = $query[1] - 1;
        my @train;
        
        my $cur = $x;
        while ($a[$cur]!=-1) {
            $cur = $a[$cur];
        }
        while ($cur!=-1) {
            push @train, $cur+1;
            $cur = $b[$cur];
        }

        my $m = scalar @train;
        print "$m @train\n";
    }
}