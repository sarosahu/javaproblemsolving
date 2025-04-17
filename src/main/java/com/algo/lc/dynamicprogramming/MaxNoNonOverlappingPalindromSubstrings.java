package com.algo.lc.dynamicprogramming;

/**
 * 2472. Maximum Number of Non-overlapping Palindrome Substrings
 * Solved
 * Hard
 * Topics
 * Companies
 * Hint
 * You are given a string s and a positive integer k.
 *
 * Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
 *
 * The length of each substring is at least k.
 * Each substring is a palindrome.
 * Return the maximum number of substrings in an optimal selection.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abaccdbbd", k = 3
 * Output: 2
 * Explanation: We can select the substrings underlined in s = "abaccdbbd". Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
 * It can be shown that we cannot find a selection with more than two valid substrings.
 * Example 2:
 *
 * Input: s = "adbcda", k = 2
 * Output: 0
 * Explanation: There is no palindrome substring of length at least 2 in the string.
 *
 *
 * Constraints:
 *
 * 1 <= k <= s.length <= 2000
 * s consists of lowercase English letters.
 */
public class MaxNoNonOverlappingPalindromSubstrings {

    /**
     * Main catch here was to avoid picking a palindromic substring with size greater than k+1
     * because if there exist a palindromic substring with size greater than k+1 then it would
     * definitely contain a palindromic substring with size at least k ( It was just basic observation )
     * And we are trying to minimize the length of the palindromic string ( with size >= k ) to
     * maximize our answer.

     * Suppose, string = abaczbzccc & k = 3

     * Without break condition, answer would have been 2 ( aba, czbzc )
     * With break condition, answer would be 3 ( aba, zbz, ccc )

     * Now observe the difference, instead of picking 'czbzc' we pick 'zbz' which allowed us to
     * further pick 'ccc' as well. I hope it clarifies the doubt.
     */
    public int maxPalindromes(String s, int k) {
        if (k == 1) {
            return s.length();
        }
        int n = s.length();
        /*
         * Building palindromeDp is wasteful it seems.
         *
        boolean[][] palindromDp = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            palindromDp[i][i] = true;
            if (i < n - 1) {
                palindromDp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            }
        }

        //buildPalindromDp(s, palindromDp);
         */
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + k - 1; j < n; ++j) {
                int len = (j - i) + 1;
                if (len > k + 1) {
                    break;
                }
                if (len >= k && isPalindrome(s, i, j) /* palindromDp[i][j] */) {
                    System.out.println("[" + i + ", " + j + "]");
                    ++ans;
                    i = j;
                    break;
                }
            }
        }
        return ans;
    }

    private void buildPalindromDp(String s, boolean[][] dp) {
        for (int i = 0; i < s.length(); ++i) {
            dp[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; ++i) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }
        for (int len = 3; len <= s.length(); ++len) {
            for (int i = 0, j = len + i - 1; j < s.length(); ++j, ++i) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
    }

    private boolean isPalindrome(String s, int l, int r, boolean [][] palindromDp) {
        if (palindromDp[l][r]) {
            return true;
        }
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return palindromDp[l][r] = true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MaxNoNonOverlappingPalindromSubstrings obj = new MaxNoNonOverlappingPalindromSubstrings();
        String s = "aabacaddadcbabc";
        int maxPalindromes = obj.maxPalindromes(s, 3);
        System.out.println("Maximum no of palindromic substring of length 3 : " + maxPalindromes);
    }
}
