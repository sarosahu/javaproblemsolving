package com.algo.ae.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring Without Duplication
 *
 * Write a function that takes in a string and returns its longest substring
 * without duplicate characters. You can assume that there will only be one
 * longest substring without duplication.
 */
public class LongestSubstringWithoutDuplication {

    // Time: O(N), Space : O(N)
    public static String longestSubstringWithoutDuplication(String str) {
        Map<Character, Integer> charToCount = new HashMap<>();
        int left = 0, right = 0;
        int maxLen = 0;
        String substr = "";

        while (right < str.length()) {
            char r = str.charAt(right);
            int rCount = charToCount.getOrDefault(r, 0);
            charToCount.put(r, rCount + 1);

            while (charToCount.get(r) > 1) {
                char l = str.charAt(left);
                int lCount = charToCount.get(l);
                charToCount.put(l, lCount - 1);
                ++left;
            }
            //maxLen = Math.max(maxLen, right - left + 1);
            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                substr = str.substring(left, right + 1);
            }
            ++right;
        }
        return substr;
    }

    // Time: O(N), Space : O(min(n,a)) -- what is a ?
    public static String longestSubstringWithoutDuplication2(String str) {
        // Write your code here
        int[] longestRange = {0, 1};
        int startIdx = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            char currChar = str.charAt(i);
            if (lastSeen.containsKey(currChar)) {
                if (longestRange[1] - longestRange[0] < i - startIdx) {
                    longestRange[0] = startIdx;
                    longestRange[1] = i;
                }
                startIdx = Math.max(startIdx, lastSeen.get(currChar) + 1);
            }
            lastSeen.put(currChar, i);
        }
        if (longestRange[1] - longestRange[0] < str.length() - startIdx) {
            longestRange[0] = startIdx;
            longestRange[1] = str.length();
        }
        return str.substring(longestRange[0], longestRange[1]);
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        String substr = longestSubstringWithoutDuplication(s);
        System.out.println("Substring : " + substr);

        substr = longestSubstringWithoutDuplication2(s);
        System.out.println("Substring2 : " + substr);
    }
}
