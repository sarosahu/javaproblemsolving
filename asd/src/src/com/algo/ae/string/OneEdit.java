package com.algo.ae.string;

/**
 * One Edit
 *
 * You're given 2 strings stringOne and stringTwo. Write a function that determines
 * if these two strings can be made equal using only one edit.
 * There are 3 possible edits:
 * Replace: One character in one string  is swapped for different character.
 * Add: One character is added at any index in one string.
 * Remove: One character is removed at any index in one string.
 * Note that both strings will contain at least one character. If the strings are
 * the same, your function should return true.
 *
 * Sample Input:
 * stringOne = "hello"
 * stringTwo = "hollo"
 * Sample Output: true // A single replace at index 1 of either string can make the strings equal
 */
public class OneEdit {
    public boolean oneEdit(String stringOne, String stringTwo) {
        int lengthOne = stringOne.length();
        int lengthTwo = stringTwo.length();
        int diff = lengthOne - lengthTwo;

        if (Math.abs(diff) > 1) {
            return false;
        }
        int numEdits = 0;

        boolean madeEdit = false;
        int indexOne = 0;
        int indexTwo = 0;

        while (indexOne < lengthOne && indexTwo < lengthTwo) {
            if (stringOne.charAt(indexOne) != stringTwo.charAt(indexTwo)) {
                if (madeEdit) {
                    return false;
                }
                madeEdit = true;

                if (lengthOne > lengthTwo) {
                    indexOne++;
                } else if (lengthTwo > lengthOne) {
                    indexTwo++;
                } else {
                    indexOne++;
                    indexTwo++;
                }
            } else {
                indexOne++;
                indexTwo++;
            }
        }

        return true;
    }
}
