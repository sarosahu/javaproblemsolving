package com.algo.ae.recursion;

import java.util.Arrays;

/**
 * Interweaving Strings
 *
 * Write a function that takes in three strings and returns a boolean representing whether
 * the third string can be formed by interweaving the first two strings.
 *
 * To interweave strings means to merge them by alternating their letters without any
 * specific pattern. For instance, the strings "abc" and "123" can be interwoven as
 * "a1b2c3", as "abc123", and as "ab1c23" (this list is non-exhaustive).
 *
 * Letters within a string must maintain their relative ordering in the interwoven string.
 *
 * Sample Input:
 *
 * one = "algoexpert"
 * two = "your-dream-job"
 * three = "your-algodream-expertjob"
 *
 * Sample Output: true
 */
public class InterweavingStrings {

    // Time: O(nm), space : O(nm) -- n is the length of the first string
    // and m is the length of the second string.
    public static boolean interweavingStringsL(String one, String two, String three) {
        int oneLen = one.length();
        int twoLen = two.length();
        int threeLen = three.length();
        if (threeLen != oneLen + twoLen) {
            return false;
        }
        boolean[][] matrix = new boolean[twoLen + 1][oneLen + 1];
        for (int i = 0; i < matrix.length; ++i) {
            Arrays.fill(matrix[i], false);
        }
        matrix[0][0] = true;
        // Fill up the first row
        for (int i = 1; i <= oneLen; ++i) {
            if (matrix[0][i-1] == true && one.charAt(i-1) == three.charAt(i-1)) {
                matrix[0][i] = true;
            }
        }
        for (int j = 1; j <= twoLen; ++j) {
            if (matrix[j-1][0] == true && two.charAt(j-1) == three.charAt(j-1)) {
                matrix[j][0] = true;
            }
        }

        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 1; j < matrix[i].length; ++j) {
                if ((matrix[i-1][j] == true && two.charAt(i-1) == three.charAt(i+j-1)) ||
                        (matrix[i][j-1] == true && one.charAt(j-1) == three.charAt(i+j-1))) {
                    matrix[i][j] = true;
                }
            }
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }

    // Time: O(2 ^ (n + m) , space : O(n + m) -- where n is the length of first string
    // and m is the length of the second string
    public static boolean interweavingStringsR(String one, String two, String three) {
        if (three.length() != one.length() + two.length()) {
            return false;
        }

        Boolean[][] cache = new Boolean[one.length() + 1][two.length() + 1];
        return areInterwoven(one, two, three, 0, 0, cache);
    }

    public static boolean areInterwoven(
            String one, String two, String three, int i, int j, Boolean[][] cache) {
        if (cache[i][j] != null) {
            return cache[i][j];
        }

        int k = i + j;
        if (k == three.length()) {
            return true;
        }

        if (i < one.length() && one.charAt(i) == three.charAt(k)) {
            cache[i][j] = areInterwoven(one, two, three, i+1, j, cache);
            if (cache[i][j]) {
                return true;
            }
        }

        if (j < two.length() && two.charAt(j) == three.charAt(k)) {
            boolean result = areInterwoven(one, two, three, i, j + 1, cache);
            cache[i][j] = result;
            return result;
        }

        cache[i][j] = false;
        return false;
    }
    public static void main(String[] args) {
        String one = "algoexpert";
        String two = "your-dream-job";
        String three = "your-algodream-expertjob";

        boolean flag = interweavingStringsL(one, two, three);
        if (flag) {
            System.out.printf("%s is interweaving of %s and %s ", three, one, two);
        } else {
            System.out.printf("%s is not interweaving of %s and %s ", three, one, two);
        }
    }
}
