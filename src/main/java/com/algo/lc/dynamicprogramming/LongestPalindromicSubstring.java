package com.algo.lc.dynamicprogramming;

/**
 * Longest Palindromic Substring
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * -------------------------------------------
 *
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 * --------------------------------------------
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class    LongestPalindromicSubstring {
    static class Boundary {
        public int start;
        public int end;
        public Boundary(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public String longestPalindrome2(String s) {

        boolean[][] palindromeDp = new boolean[s.length()][s.length()];
        Boundary boundary = new Boundary(0, 0);
        int maxVal = 1;
        for (int end = 0; end < s.length(); ++end) {
            for (int start = 0; start <= end; ++start) {
                char startChar = s.charAt(start);
                char endChar = s.charAt(end);
                if (startChar == endChar && (end - start <= 2 || palindromeDp[start + 1][end - 1])) {
                    palindromeDp[start][end] = true;
                    if (end - start + 1 > maxVal) {
                        maxVal = end - start + 1;
                        boundary.start = start;
                        boundary.end = end;
                    }
                }
            }
        }
        return s.substring(boundary.start, boundary.end + 1);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); ++i) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        return r - l - 1;
    }
}
