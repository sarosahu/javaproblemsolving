package com.algo.ae.string;

/**
 * Palindrome Check
 *
 * Write a function that takes in a non-empty string and that returns a
 * boolean representing whether the string is a palindrome.
 *
 * A palindrome is defined as a string that's written the same forward and
 * backward. Note that single-character strings are palindromes.
 *
 * Sample Input:
 * string = "abcdcba"
 *
 * Sample Output: true // it's written the same forward and backward.
 *
 */
public class PalindromeCheck {

    // { Recursive approach.
    // Time : O(N), Space : O(N)
    public static boolean isPalindromeR(String str) {
        return isPalindromeR(str, 0);
    }

    public static boolean isPalindromeR(String str, int i) {
        int j = str.length() - 1 - i;
        return i >= j ? true :
                str.charAt(i) == str.charAt(j) && isPalindromeR(str, i + 1);
    }
    // }

    // { Time: O(n), Space : O(1)
    public static boolean isPalindromeL(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    // }
}
