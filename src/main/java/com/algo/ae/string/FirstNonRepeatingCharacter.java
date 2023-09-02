package com.algo.ae.string;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * First Non-Repeating Character
 *
 * Write a function that takes in a string of lowercase English-alphabet
 * letters and returns the index of the string's first non-repeating
 * character.
 *
 * The first non-repeating character is the first character in a string
 * that occurs only once.
 *
 * If the input string doesn't have any non-repeating characters, your
 * function should return -1.
 *
 * Sample Input:
 * string = "abcdcaf"
 *
 * Sample Output: 1
 * The first non-repeating character is "b" and is found at index 1.
 */
public class FirstNonRepeatingCharacter {
    // Time : O(N), Space : O(N)
    public int firstNonRepeatingCharacter(String string) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (freq.get(c) == 1) {
                return i;
            }
        }

        return -1;
    }

    // Time O(N^2), Space : O(1)
    public int firstNonRepeatingCharacter2(String string) {
        for (int i = 0; i < string.length(); ++i) {
            boolean foundDuplicate = false;
            for (int j = 0; j < string.length(); ++j) {
                if (string.charAt(i) == string.charAt(j) && i != j) {
                    foundDuplicate = true;
                }
            }
            if (!foundDuplicate) {
                return i;
            }
        }
        return -1;
    }

    // Time: O(N), Space : O(N)
    public int firstNonRepeatingCharacter3(String string) {
        Map<Character, Integer> indexes = new LinkedHashMap<>();
        for (int i = 0; i < string .length(); ++i) {
            char c = string.charAt(i);
            if (indexes.containsKey(c)) {
                indexes.put(c, -1);
            } else {
                indexes.put(c, i);
            }
        }

        for (Map.Entry<Character, Integer> entry : indexes.entrySet()) {
            if (!entry.getValue().equals(-1)) {
                return entry.getValue();
            }
        }
        return -1;
    }
}
