package com.algo.ae.dp;

import java.util.Arrays;

/**
 * Palindrome Partitioning Min Cuts
 *
 * Given a non-empty string, write a function that returns the minimum number of cuts needed
 * to perform on the string such that each remaining substring is a palindrome.
 *
 * A palindrome is defined as a string that's written the same forward as backward. Note that
 * single-character strings are palindromes.
 *
 * Sample Input:
 * string = "noonabbad"
 *
 * Sample Output: 2
 * // noon | abba | d
 */
public class PalindromicPartitioningWithMinimumCuts {

    // Brute-force approach, Time: O(N^3), Space: O(N^2)
    public static int palindromePartitioningMinCuts(String str) {
        boolean[][] palindromes = new boolean[str.length()][str.length()];
        for (int i = 0; i < str.length(); ++i) {
            for (int j = i; j < str.length(); ++j) {
                palindromes[i][j] = isPalindrome(str.substring(i, j + 1));
            }
        }

        int [] cuts = new int[str.length()];
        Arrays.fill(cuts, Integer.MAX_VALUE);
        for (int i = 0; i < str.length(); ++i) {
            if (palindromes[0][i]) {
                cuts[i] = 0;
            } else {
                cuts[i] = cuts[i - 1] + 1;
                for (int j = 1; j < i; ++j) {
                    if (palindromes[j][i] && cuts[j - 1] + 1 < cuts[i]) {
                        cuts[i] = cuts[j - 1] + 1;
                    }
                }
            }
        }
        return cuts[str.length() - 1];
    }

    public static boolean isPalindrome(String str) {
        int leftIdx = 0, rightIdx = str.length() - 1;
        while (leftIdx < rightIdx) {
            if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
                return false;
            }
            ++leftIdx;
            --rightIdx;
        }
        return true;
    }

    // Time: O(N^2), Space: O(N^2)
    public static int palindromePartitioningMinCutsE(String str) {
        int n = str.length();
        int [] cutsDp = new int[n];
        boolean[][] palindromeDp = new boolean[n][n];
        for (int end = 0; end < n; ++end) {
            int minimumCut = end;
            for (int start = 0; start <= end; ++start) {
                // Check if substring (start, end) is a palindrome
                if (str.charAt(start) == str.charAt(end) &&
                        (end - start <= 2 || palindromeDp[start + 1][end - 1])) {
                    palindromeDp[start][end] = true;
                    minimumCut = start == 0 ? 0 :
                            Math.min(minimumCut, 1 + cutsDp[start - 1]);
                }
            }
            cutsDp[end] = minimumCut;
        }
        return cutsDp[str.length() - 1];
    }

    public static void main(String[] args) {
    }
}
