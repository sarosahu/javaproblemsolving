package com.algo.ae.famous;

import java.util.Arrays;

/**
 * Knuth--Morris -- Pratt Algorithm
 *
 * Write a function that takes in two strings and checks if the first string contains the
 * second one using Knuth--Morris--Pratt algorithm. The function should return a boolean.
 *
 * Sample Input:
 * string = "aefoaefcdaefcdaed"
 * substring = "aefcdaed"
 *
 * Sample Output: true
 */
public class KnuthMorrisPrattAlgorithm {

    // Time : O(n + m) | space : O(m)
    public static boolean knuthMorrisPrattAlgorithm(String text, String pattern) {
        // Find out the longest prefix which is a suffix
        int[] lps = buildPattern(pattern);
        return isMatching(text, pattern, lps);
    }

    public static int[] buildPattern(String pattern) {
        int[] lps = new int[pattern.length()];
        Arrays.fill(lps, -1);
        int j = 0, i = 1;
        while(i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j;
                ++i;
                ++j;
            } else if (j > 0) {
                j = lps[j - 1] + 1;
            } else {
                ++i;
            }
        }
        return lps;
    }

    public static boolean isMatching(String text, String pattern, int[] lps) {
        int i = 0, j = 0;

        while (i + pattern.length() - j <= text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    return true;
                }
                ++i;
                ++j;
            } else if (j > 0) {
                j = lps[j - 1] + 1;
            } else {
                ++i;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String [] texts = {"aefoaefcdaefcdaed", "Helloworld", "edu"};
        String [] patterns = {"aefcdaed", "World", "edu"};
        for (int i = 0; i < texts.length; ++i) {
            String text = texts[i];
            String pattern = patterns[i];
            if (knuthMorrisPrattAlgorithm(text, pattern)) {
                System.out.println("Pattern \"" + pattern + "\" is found within \"" + text + "\"");
            } else {
                System.out.println("Pattern \"" + pattern + "\" is not found within \"" + text + "\"");
            }
        }
    }
}
