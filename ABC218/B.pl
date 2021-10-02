use strict;
use warnings;

chomp(my $line = <STDIN>);
my @p = split / /, $line;

my @s = split //, "abcdefghijklmnopqrstuvwxyz";
my @ans = map {$s[$_-1]} @p;
print @ans, "\n";