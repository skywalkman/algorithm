#!/usr/local/bin/perl5 -w

use List::Util qw(max);

#my $str_t = "123abcba123aba11211a1113111";
my $str_t = "111221abba";

my $res = _longestpd($str_t, 0);
print "the longest length is $res\n";

sub _longestpd {
    my $str = shift;
    my $cnt = shift;

    my $len = length($str);

    if ($len == 0) {
        return $cnt;
    }
    elsif ($len == 1) {
        return $cnt + 1;
    }
    elsif ($len == 2) {
        if ((substr $str, 0, 1) eq (substr $str, 1, 1)) {
            return $cnt + 2;
        }
        else {
            return 1;
        }
    }

    if ((substr $str, 0, 1) eq (substr $str, $len-1, 1)) {
        my $str_inner = substr $str, 1, $len-2;
        my $str_l = substr $str, 0, $len-1;
        my $str_r = substr $str, 1, $len-1;

        my $res_l = _longestpd($str_l, 0);
        my $res_r = _longestpd($str_r, 0);
        my $res_inner =  _longestpd($str_inner, $cnt+2);

        return max ($res_l, $res_r, $res_inner);

    }
    else {
        my $str_l = substr $str, 0, $len-1;
        my $str_r = substr $str, 1, $len-1;

        my $res_l = _longestpd($str_l, 0);
        my $res_r = _longestpd($str_r, 0);

        return max ($res_l, $res_r);
    }
}
