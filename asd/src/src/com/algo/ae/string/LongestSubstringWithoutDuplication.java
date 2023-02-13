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
        // Sliding window approach
        String longSubstr = "";
        int left = 0, right = 0;
        Map<Character, Integer> hm = new HashMap<>();
        while (right < str.length()) {
            char currChar = str.charAt(right);
            int rightCount = hm.getOrDefault(currChar, 0);
            if (rightCount > 0) {
                // currChar already there in hashMap
                String currLongSubstr = str.substring(left, right);
                if (currLongSubstr.length() > longSubstr.length()) {
                    longSubstr = currLongSubstr;
                }
                // Remove all the characters from left till last visited character with value currChar
                while (str.charAt(left) != currChar) {
                    hm.remove(str.charAt(left));
                    ++left;
                }
                left += 1;
            } else {
                hm.put(currChar, 1);
            }
            ++right;
        }
        String lastLongSubstr = str.substring(left, right);
        if (lastLongSubstr.length() > longSubstr.length()) {
            longSubstr = lastLongSubstr;
        }
        return longSubstr;
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
}
