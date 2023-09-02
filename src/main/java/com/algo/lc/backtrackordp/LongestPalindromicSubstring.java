package com.algo.lc.backtrackordp;

public class LongestPalindromicSubstring {
    static class Boundary {
        public int start;
        public int end;
        public Boundary(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

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

    public static String
    longestPalindrome2(String s) {
        if (s == null || s.isEmpty()) {
            return "";
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

    public static int
    expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        String s = "abcmnnmcb";
        String longestPalindromicSubstring = longestPalindrome(s);
        String longestPalindromicSubstring2 = longestPalindrome2(s);
        System.out.println("Longest palindromic substring in " + s + " is : " + longestPalindromicSubstring);
        System.out.println("Longest palindromic substring in " + s + " is : " + longestPalindromicSubstring2);
    }
}
