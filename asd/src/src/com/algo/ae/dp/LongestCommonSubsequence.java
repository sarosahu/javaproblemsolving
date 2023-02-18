package com.algo.ae.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Longest Common Subsequence
 *
 * Write a function that takes in two strings and returns their longest
 * common subsequence.
 *
 * A subsequence of a string is a set of characters that aren't necessarily
 * adjacent in the string but that are in the same order as they appear in
 * the string. For instance, the characters["a", "c", "d"] form a
 * subsequence of the string "abcd", and so do the characters ["b", "d"].
 * Note that a single character in a string and the string itself are both
 * valid subsequences of the string.
 *
 * You can assume that there will only be one longest common subsequence.
 *
 * Sample Input:
 * str1 = "ZXVVYZW"
 * str2 = "XKYKZPW"
 *
 * Sample Output:
 * ["X", "Y", "Z", "W"]
 */
public class LongestCommonSubsequence {

    // O(nm), space: O(nm)
    public static List<Character> longestCommonSubsequence(String str1, String str2) {
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
        List<Character> subsequenceList = buildSubsequence(matrix, str2);
        Collections.reverse(subsequenceList);

        return subsequenceList;
    }

    private static List<Character> buildSubsequence(int [][] matrix, String str) {
        List<Character> list = new ArrayList<>();
        int i = matrix.length - 1;
        int j = matrix[matrix.length - 1].length - 1;
        while (i > 0 && j > 0) {
            if (matrix[i][j] == matrix[i-1][j]) {
                --i;
            } else if (matrix[i][j] == matrix[i][j-1]) {
                --j;
            } else {
                list.add(str.charAt(j-1));
                --i;
                --j;
            }
        }
        return list;
    }
}
