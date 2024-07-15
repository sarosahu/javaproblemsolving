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
        if (s == null || s.isEmpty()) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        Boundary boundary = new Boundary(0, 0);
        for (int i = 0; i < s.length() - 1; ++i) {
            expandAroundCenter(s, i, i, boundary);
            if (s.charAt(i) == s.charAt(i + 1)) {
                expandAroundCenter(s, i, i + 1, boundary);
            }
        }
        return s.substring(boundary.start, boundary.end + 1);
    }

    private void
    expandAroundCenter(String s, int left, int right, Boundary boundary) {
        int l = left, r = right;
        while (l > 0 && r < s.length() - 1 && s.charAt(l - 1) == s.charAt(r + 1)) {
            --l;
            ++r;
        }
        int currLen = r - l + 1;
        if (currLen > boundary.end - boundary.start + 1) {
            boundary.start = l;
            boundary.end = r;
        }
    }

    public static void main(String[] args) {
        String s = "acdedcded";
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
        String longestPalindromicSubstring = obj.longestPalindrome2(s);
        System.out.println("Longest palindromic substring 2: " + longestPalindromicSubstring);

        longestPalindromicSubstring = obj.longestPalindrome(s);
        System.out.println("Longest palindromic substring : " + longestPalindromicSubstring);
    }
}
