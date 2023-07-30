package com.algo.lc.dynamicprogramming;

import java.util.Collections;
import java.util.List;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some
 * characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 *
 * Note: If you want to return the string that represent the common sequence, then
 * refer .../asd/src/src/com/algo/ae/dp/LongestCommonSubsequence.java
 */
public class LongestCommonSubsequence {

    /**
     * Complexity Analysis
     *
     * Time complexity : O(M⋅N).
     *
     * This time, solving each sub-problem has a cost of O(1). Again, there are M⋅N sub-problems,
     * and so we get a total time complexity of O(M⋅N).
     *
     * Space complexity : O(M⋅N)
     *
     * We need to store the answer for each of the M⋅N sub-problems.
     */
    public int longestCommonSubsequenceR(String text1, String text2) {
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];
        // Initialize memo to -1's so that we know whether a value has been
        // filled in or not. Keep the base cases as 0's to simplify a later code a bit.
        for (int i = 0; i < text1.length(); ++i) {
            for (int j = 0; j < text2.length(); ++j) {
                memo[i][j] = -1;
            }
        }

        return lcsHelper(0, 0, text1, text2, memo);
    }

    private int lcsHelper(int p1, int p2, String text1, String text2, int[][] memo) {
        // Check if we have already solved this sub-problem. This also covers the
        // base cases where p1 == text1.length or p2 == text2.length
        if (memo[p1][p2] != -1) {
            return memo[p1][p2];
        }

        // Recursive cases.
        int answer = 0;
        if (text1.charAt(p1) == text2.charAt(p2)) {
            answer = 1 + lcsHelper(p1 + 1, p2 + 1, text1, text2, memo);
        } else {
            answer = Math.max(lcsHelper(p1, p2 + 1, text1, text2, memo),
                    lcsHelper(p1 + 1, p2, text1, text2, memo));
        }

        // Add the best answer to the memo before returning it.
        memo[p1][p2] = answer;
        return memo[p1][p2];
    }

    /**
     * Complexity Analysis
     *
     * Time complexity : O(M⋅N).
     *
     * We're solving M⋅N sub-problems. Solving each sub-problem is an O(1) operation.
     *
     * Space complexity : O(M⋅N).
     *
     * We're allocating a 2D array of size M⋅N to save the answers to sub-problems.
     */
    public int longestCommonSubsequenceBottomUp(String str1, String str2) {
        // Write your code here.
        int [][] matrix = new int [str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < matrix[0].length; ++i) {
            matrix[0][i] = 0;
        }
        for (int i = 0; i < matrix.length; ++i) {
            matrix[i][0] = 0;
        }

        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 1; j < matrix[i].length; ++j) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }

        return matrix[str1.length()][str2.length()];
    }

    /**
     * Complexity Analysis
     *
     * Let MMM be the length of the first word, and NNN be the length of the second word.
     *
     * Time complexity : O(M⋅N).
     *
     * Like before, we're solving M⋅N subproblems, and each is an O(1) operation to solve.
     *
     * Space complexity : O(min(M,N)).
     *
     * We've reduced the auxiliary space required so that we only use two 1D arrays at a time;
     * each the length of the shortest input word. Seeing as the 222 is a constant, we drop it,
     * leaving us with the minimum length out of the two words.
     */
    public int longestCommonSubsequenceSpaceOptimized(String text1, String text2) {
        // Bottom up DP -- optimizing space:
        // We want to have text1.length() < text2.length()
        // Because we want to optmize the space and our logic wants it.
        // If text1 doesn't reference the shortest string, swap them
        if (text2.length() < text1.length()) {
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        // The previous and current column starts with all 0's
        int[] prev = new int[text1.length() + 1];
        int[] curr = new int[text1.length() + 1];

        // Iterate through each column, starting from the last one.
        // Note: text1 appears like a row and text2 appears like a column in tabulation
        for (int col = text2.length() - 1; col >= 0; --col) {
            for (int row = text1.length() - 1; row >= 0; --row) {
                if (text1.charAt(row) == text2.charAt(col)) {
                    curr[row] = 1 + prev[row + 1];
                } else {
                    curr[row] = Math.max(prev[row], curr[row + 1]);
                }
            }
            // Swap curr and prev
            // Note: We want curr to be new prev, not really the vice versa, but
            // we need to do this vice versa, because prev space can be reused.
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[0];
    }

    public static void main(String[] args) {
        String text1 = "actgattag";
        String text2 = "gtgtgatcg";
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        int result = obj.longestCommonSubsequenceR(text1, text2);
        System.out.println("The longest common subsequences : " + result);
        result = obj.longestCommonSubsequenceSpaceOptimized(text1, text2);
        System.out.println("The longest common subsequences : " + result);
        result = obj.longestCommonSubsequenceBottomUp(text1, text2);
        System.out.println("The longest common subsequences : " + result);
    }
}
