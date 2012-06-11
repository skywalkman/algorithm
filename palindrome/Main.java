/************************************************************
 *
 * SPOJ PLD problem
 * 
 * Input:
 * first line contains K, second line S
 * 
 * Output:
 * number of palindromes having length as K in S
 *
 * This is a modification from:
 * http://algs4.cs.princeton.edu/53substring/Manacher.java.html
 * Still get TLE in SPOJ system...
 *
 * Version 0.01 Jun 11 2012
 *
 ***********************************************************/

import java.io.*;
import java.util.Scanner;

public class Main {
    private static int[]  p;    // p[i] = length of longest palindrome substring, centered at i
    private static String s;    // original string
    private static char[] t;    // transformed string

    public static char[] preprocess(String s) {
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
    
    public static int count(int len) {
    	  int res = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i] == len || (p[i] > len && (p[i] - len) % 2 == 0))
                res++;
        }

        return res;
    }

    public static void main (String args[]) {
        int len;

        Scanner scan = new Scanner(System.in);
        len = Integer.valueOf(scan.nextLine());
        s = scan.nextLine();

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

        int res = count(len);

        System.out.println(res);
    }
}

