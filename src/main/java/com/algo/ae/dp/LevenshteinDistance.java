package com.algo.ae.dp;

/**
 * Levenshtein Distance
 *
 * Write a function that takes in 2 strings and returns the minimum number edit
 * operations that need to be performed on the first string to obtain the
 * second string.
 *
 * There are 3 edit operations: insertion of a character, deletion of a character
 * and substitution of a character for another.
 *
 * Sample Input:
 * str1 = "abc"
 * str2 = "yabd"
 *
 * Sample Output: 2 // Insert "y", substitute "c" for "d"
 */
public class LevenshteinDistance {
    // Time: O(nm), Space: O(nm) space
    public static int levenshteinDistance(String str1, String str2) {
        int [][] dist = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); ++i) {
            dist[i][0] = i;
        }
        for (int i = 1; i <= str2.length(); ++i) {
            dist[0][i] = i;
        }
        for (int i = 1; i < dist.length; ++i) {
            for (int j = 1; j < dist[i].length; ++j) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dist[i][j] = dist[i-1][j-1];
                } else {
                    dist[i][j] = Math.min(dist[i-1][j-1], Math.min(dist[i-1][j], dist[i][j-1])) + 1;
                }
            }
        }
        return dist[str1.length()][str2.length()];
    }

    // Time: O(nm), Space: O(nm) space
    public static int levenshteinDistance2(String str1, String str2) {
        // Write your code here.
        int [][] dist = new int[str2.length() + 1][str1.length() + 1];
        for (int i = 0; i <= str2.length(); ++i) {
            dist[i][0] = i;
        }
        for (int i = 1; i <= str1.length(); ++i) {
            dist[0][i] = i;
        }

        for (int i = 1; i < dist.length; ++i) {
            for (int j = 1; j < dist[i].length; ++j) {
                if (str2.charAt(i-1) == str1.charAt(j-1)) {
                    dist[i][j] = dist[i-1][j-1];
                } else {
                    dist[i][j] = Math.min(dist[i-1][j-1], Math.min(dist[i-1][j], dist[i][j-1])) + 1;
                }
            }
        }
        return dist[str2.length()][str1.length()];
    }

    // Time : O(NM), Space : O(min(n, m)
    public static int levenshteinDistanceE(String str1, String str2) {
        // Write your code here.
        String small = str1.length() < str2.length() ? str1 : str2;
        String big = str1.length() >= str2.length() ? str1 : str2;

        int [] d = new int[small.length()+1];
        for (int i = 1; i <= big.length(); ++i) {
            int prevCornor = d[0];  // Previous cornor indices
            d[0] = i;
            for (int j = 1; j <= small.length(); ++j) {
                int prevUp = d[j]; // Previous up indices (e.g. i-1, j previous row , same columm)
                d[j] = big.charAt(i-1) == small.charAt(j-1) ?
                        prevCornor : 1 + Math.min(prevCornor, Math.min(d[j-1], prevUp));

                prevCornor = prevUp;
            }
        }
        return d[d.length-1];
    }
}
