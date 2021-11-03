use strict;
use warnings;
use utf8;

my @s;
for (my $i = 0; $i < 3; $i++) {
    chomp(my $line = <>);
    push @s, $line;
}

chomp(my $t = <>);
my @ans = map {$s[$_-1]} split //, $t;
print @ans, "\n";