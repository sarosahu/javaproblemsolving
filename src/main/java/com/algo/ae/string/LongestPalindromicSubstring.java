package com.algo.ae.string;

/**
 * Longest Palindromic Substring
 *
 * Write a function that, given a string, returns its longest palindromic substring.
 *
 * A palindrome is defined as a string that is written the same forward and backward.
 * Note that single-character strings are palindromes.
 *
 * You can assume that there will only be one longest palindromic substring.
 *
 * Sample Input: "abaxyzzyxf"
 *
 * Sample Output: "xyzzyx"
 */
public class LongestPalindromicSubstring {
    static class Boundary {
        int start;
        int end;
        Boundary(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    // Time : O(N^3), Space O(N)
    public static String longestPalindromicSubstring(String str) {
        String longest = "";
        for (int i = 0; i < str.length(); ++i) {
            for (int j = i; j < str.length(); ++j) {
                String substring = str.substring(i, j + 1);
                if (substring.length() > longest.length() && isPalindrome(substring)) {
                    longest = substring;
                }
            }
        }
        return longest;
    }

    public static boolean isPalindrome(String str) {
        int leftIdx = 0;
        int rightIdx = str.length() - 1;
        while (leftIdx < rightIdx) {
            if (str.charAt(leftIdx) != str.charAt(rightIdx)) {
                return false;
            }
            ++leftIdx;
            --rightIdx;
        }
        return true;
    }

    //Time: O(N^2), space : O(N)
    public static String longestPalindromicSubstringE(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        if (str.length() <= 1) {
            return str;
        }
        int start = 0, end = 0;
        for (int i = 0; i < str.length(); ++i) {
            int len1 = expandAroundCenter(str, i, i);
            int len2 = expandAroundCenter(str, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1)/2;
                end = i + len / 2;
            }
        }
        return str.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    // Dynamic programming approach
    // Time : O(N^2), Space : O(N^2)
    public static String longestPalindromicSubstringE2(String str) {
        if (str.length() <= 1) {
            return str;
        }
        boolean[][] palindromDp = new boolean[str.length()][str.length()];
        Boundary boundary = new Boundary(0, 0);
        int maxVal = 1;

        for (int end = 0; end < str.length(); ++end) {
            for (int start = 0; start <= end; ++start) {
                char startChar = str.charAt(start);
                char endChar = str.charAt(end);

                if (startChar == endChar && (end - start <= 2 ||
                    palindromDp[start + 1][end - 1])) {
                    palindromDp[start][end] = true;

                    if (end - start + 1 > maxVal) {
                        maxVal = end - start + 1;
                        boundary.start = start;
                        boundary.end = end;
                    }
                }
            }
        }
        return str.substring(boundary.start, boundary.end + 1);
    }

    public static void main(String[] args) {
        String str = "abaxyzzyxf";
        String longestPalindromicSubstring1 = longestPalindromicSubstring(str);
        System.out.println("Longest palindromic substring : " + longestPalindromicSubstring1);

        String longestPalindromicSubstring2 = longestPalindromicSubstringE(str);
        System.out.println("Longest palindromic substring : " + longestPalindromicSubstring2);

        String longestPalindromicSubstring3 = longestPalindromicSubstringE2(str);
        System.out.println("Longest palindromic substring : " + longestPalindromicSubstring3);
    }
}
