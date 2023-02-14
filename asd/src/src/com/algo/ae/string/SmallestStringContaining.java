package com.algo.ae.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Smallest Substring Containing
 *
 * You're given 2 non-empty strings: a big string and a small string. Write a
 * function that returns the smallest substring in the big string that contains
 * all of the small string's characters.
 *
 * Note that:
 *  - The substring can contain other characters not found in the small string.
 *  - The characters in the substring don't have to be in the same order as they
 *    appear in the small string.
 *  - If the small string has duplicate characters, the substring has to contain
 *    those duplicate characters(it can also contain more, but not fewer).
 *
 *  You can assume that there will only be one relevant smallest substring.
 *
 *  Sample Input:
 *  bigString = "abcd$ef$axb$c$"
 *  smallString = "$$abf"
 *
 *  Sample Ouput: "f$axb$"
 */
public class SmallestStringContaining {
    public static String smallestSubstringContaining(String bigString, String smallString) {
        // Write your code here.
        // Store all the characters from smallString into a hash table.
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < smallString.length(); ++i) {
            char letter = smallString.charAt(i);
            int count = hm.getOrDefault(letter, 0);
            hm.put(letter, count + 1);
        }

        int left = 0, right = 0;
        int[] range = {0, bigString.length() - 1};
        Map<Character, Integer> currentStore = new HashMap<>();
        int currSize = 0;
        boolean found = false;
        while (right < bigString.length()) {
            char currChar = bigString.charAt(right);
            if (hm.containsKey(currChar)) {
                int count = currentStore.getOrDefault(currChar, 0);
                ++count;
                currentStore.put(currChar, count);
                if (count == hm.get(currChar)) {
                    ++currSize;
                }
            }

            while (currSize == hm.size() && left <= right) {
                found = true;
                if (range[1] - range[0] > right - left) {
                    range[0] = left;
                    range[1] = right;
                }

                char lastChar = bigString.charAt(left);
                if (currentStore.containsKey(lastChar)) {
                    int count = currentStore.get(lastChar);
                    currentStore.put(lastChar, count - 1);
                    if (currentStore.get(lastChar) < hm.get(lastChar)) {
                        --currSize;
                    }
                }
                ++left;
            }
            ++right;
        }
        return found == false ? "" : bigString.substring(range[0], range[1] + 1);
    }

    // O(b + s) time | space : O(b + s)
    // b and s are length of big and small strings
    public static String smallestSubstringContaining2(String bigString, String smallString) {
        // Write your code here.
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < smallString.length(); ++i) {
            char letter = smallString.charAt(i);
            int count = hm.getOrDefault(letter, 0);
            hm.put(letter, count + 1);
        }

        int left = 0, right = 0;
        int[] range = {0, 0};
        Map<Character, Integer> currentStore = new HashMap<>();
        int currSize = 0;
        while (right < bigString.length()) {
            char currChar = bigString.charAt(right);
            if (hm.containsKey(currChar)) {
                int count = currentStore.getOrDefault(currChar, 0);
                ++count;
                currentStore.put(currChar, count);
                if (count == hm.get(currChar)) {
                    ++currSize;
                }
            }

            while (currSize == hm.size() && left <= right) {
                if (range[0] == 0 && range[1] == 0 || range[1] - range[0] > right - left + 1) {
                    range[0] = left;
                    range[1] = right + 1;
                }
                ++left;
                char lastChar = bigString.charAt(left - 1);
                if (currentStore.containsKey(lastChar)) {
                    int count = currentStore.get(lastChar);
                    currentStore.put(lastChar, count - 1);
                    if (currentStore.get(lastChar) < hm.get(lastChar)) {
                        --currSize;
                    }
                }
            }
            ++right;
        }
        return bigString.substring(range[0], range[1]);
    }
}
