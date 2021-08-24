#!/usr/bin/perl

use v5.10;
use strict;
use warnings;

chomp(my $s = <STDIN>);
say(($s eq "Hello,World!") ? "AC" : "WA");