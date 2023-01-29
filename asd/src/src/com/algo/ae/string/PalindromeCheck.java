package com.algo.ae.string;

public class PalindromeCheck {
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
