package com.algo.ctci.arraystring;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you can't use additional data structures ?
 * What if there are only a-z and A-Z characters, and you are allowed to use
 * any data structure and how can you optimize space ?
 *
 * Note: Assume there is no unicode character and no extended ascii characters (
 * character set from 128 till 255)
 */
public class HasUniqueCharacters {
    /**
     * Time: O(N), space: O(1), because loop will never iterate over 128
     */
    public static boolean isUnique(String str) {
        if (str.length() > 128) {
            return false;
        }
        boolean [] char_set = new boolean[128];
        for (char c : str.toCharArray()) {
            int val = c;
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /**
     * If the input string has only characters a-z, A-Z
     */
    public static boolean hasUniqueLetters(String str) {
        long checker = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if ((checker & (1L << (c - 'a'))) > 0) {
                return false;
            }
            checker |= 1L << (c - 'a');
        }
        return true;
    }

    public static void main(String[] args) {
        String [] strs = {
                "abcdefghijkl",
                "AabcdefghIJKlmnoPQrstUVWxyZB",
                "AabcdefghIJKlmnoPQrstUVWxyZBq",
                "AabcdefghIJKlmnoPQrstUVWxyZBg",
        };

        for (String str : strs) {
            boolean hasUnique1 = isUnique(str);
            if (hasUnique1) {
                System.out.println(str + " has unique characters");
            } else {
                System.out.println(str + " has no unique characters");
            }
            hasUnique1 = hasUniqueLetters(str);
            if (hasUnique1) {
                System.out.println(str + " has unique characters");
            } else {
                System.out.println(str + " has no unique characters");
            }
            System.out.println();
        }
    }
}
