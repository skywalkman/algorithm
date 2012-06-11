/************************************************************
 *
 * Compute the longest palindrome substring in linear time
 * using Manacher's algorithm.
 *
 * http://algs4.cs.princeton.edu/53substring/Manacher.java.html
 * http://www.leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 *
 ************************************************************/

public class Manacher {
    private int[]  p;    // p[i] = length of longest palindrome substring, centered at i
    private String s;    // original string
    private char[] t;    // transformed string

    public Manacher (String s) {
        this.s = s;
        t = preprocess(s);
        p = new int[t.length];

        int center = 0, right = 0;
        for (int i = 1; i < t.length-1; i++) {
            int mirror = 2*center - i;

            if (right > i) p[i] = Math.min(right - i, p[mirror]);

            // attempt to expand palindrome centered at i
            while (t[i + (1 + p[i])] == t[i - (1 + p[i])]) 
                p[i]++;
            
            // if palindrome centered at i expands past right,
            // adjust center based on expanded palindrome
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }
    }

    // Transform s into t
    // For example, if s = "abba", then t = "$#a#b#b#a#@"
    public char[] preprocess(String s) {
        char[] t = new char[s.length()*2 + 3];
        t[0] = '$';
        t[s.length()*2 + 2] = '@';
        for (int i = 0; i < s.length(); i++) {
            t[2*i + 1] = '#';
            t[2*i + 2] = s.charAt(i);
        }
        t[s.length()*2 + 1] = '#';
        return t;
    }

    // longest palindrome substring
    public String longestPalindromicSubstring() {
        int length = 0;
        int center = 0;
        for (int i = 1; i < p.length-1; i++) {
            if (p[i] > length) {
                length = p[i];
                center = i;
            }
        }
        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
    }

    public String longestPalindromicSubstring(int i) {
        i = i + 2;
        int length = p[i];
        int center = i;
        return s.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
    }

    public void printAllLength() {
        for (int i = 0; i < p.length; i++)
            StdOut.print(p[i]);

        StdOut.println();
    }


    public static void main (String[] args) {
        String s = args[0];
        Manacher manacher = new Manacher(s);
        manacher.printAllLength();
        StdOut.println(manacher.longestPalindromicSubstring());
        for (int i = 0; i < 2*s.length(); i++)
            StdOut.println(i + ": " + manacher.longestPalindromicSubstring(i));
    }
}
