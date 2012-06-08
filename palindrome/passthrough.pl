#!/usr/local/bin/perl5 -w

use List::Util qw(max);

#my $str_t = "123abcba123aba11211a11133111";
my $str_t = "FourscoreandsevenyearsagoourfaathersbroughtforthonthiscontainentanewnationconceivedinzLibertyanddedicatedtothepropositionthatallmenarecreatedequalNowweareengagedinagreahtcivilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";


my ($cnt_res, $str_res) = _longestpd($str_t);
print "the longest length is $cnt_res, and the sub string is $str_res\n";

sub _longestpd {
    my $str = shift;

    my $len = length($str);
    my $max = 0;
    my $str_res = qq{};

    return $len if $len < 2;

    my $i;

    for ($i = 1; $i < $len; $i++) {
        if ((substr $str, $i-1, 1 ) eq (substr $str, $i, 1)) {
            my ($m, $n, $len_tmp);
            $m = $i-2;
            $n = $i+1;
            $len_tmp = 2;

            while($m >= 0 && $n < $len && (substr $str, $m, 1) eq (substr $str, $n, 1)) {
                $m--;
                $n++;
                $len_tmp += 2;
            }

            if ( $len_tmp > $max ) {
                $str_res = substr $str, $m+1, $len_tmp;
                $max = $len_tmp;
            }
        }

        {
            my ($m, $n, $len_tmp);
            $m = $i-1;
            $n = $i+1;
            $len_tmp = 1;

            while($m >= 0 && $n < $len && (substr $str, $m, 1) eq (substr $str, $n, 1)) {
                $m--;
                $n++;
                $len_tmp += 2;
            }

            if ( $len_tmp > $max ) {
                $str_res = substr $str, $m+1, $len_tmp;
                $max = $len_tmp;
            }
        }
    }

    return ($max, $str_res);
}
