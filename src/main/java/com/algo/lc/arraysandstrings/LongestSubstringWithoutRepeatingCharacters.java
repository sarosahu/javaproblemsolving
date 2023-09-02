package com.algo.lc.arraysandstrings;
/**
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3047/
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 ***********************************************************/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    // BF : Time: O(n^3)
    public static int
    lengthOfLongestSubstring(String str) {
        int n = str.length();
        int result = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (hasNonRepeatingCharacters(str, i, j)) {
                    result = Math.max(result, j - i + 1);
                }
            }
        }

        return result;
    }

    private static boolean
    hasNonRepeatingCharacters(String str, int start, int end) {
        Set<Character> chars = new HashSet<>();
        for (int i = start; i <= end; ++i) {
            char currChar = str.charAt(i);
            if (chars.contains(currChar)) {
                return false;
            }
            chars.add(currChar);
        }
        return true;
    }

    // Time : O(2n) -> O(n), space: O(min(m, n))
    // m : size of character, n is length of string
    public static int
    lengthOfLongestSubstringE1(String str) {
        Map<Character, Integer> chars = new HashMap<>();
        int maxLen = 1;
        int left = 0, right = 0;

        while (right < str.length()) {
            char currChar = str.charAt(right);
            int countOfCurrChar = chars.getOrDefault(currChar, 0);
            chars.put(currChar, countOfCurrChar + 1);

            while (chars.get(currChar) > 1) {
                char leftChar = str.charAt(left);
                int countOfLeftChar = chars.get(leftChar);
                chars.put(leftChar, countOfLeftChar - 1);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            ++right;
        }
        return maxLen;
    }

    // Time: O(n), space: O(min(m, n))
    // m : size of charset/alphabet, n : length of string
    public static int
    lengthOfLongestSubstringE2(String s) {
        int len = s.length();
        if (len == 0 || len == 1) {
            return len;
        }
        int res = 0;
        Map<Character, Integer> charToIndex = new HashMap<>();

        for (int endIndex = 0, startIndex = 0; endIndex < len; ++endIndex) {
            char endChar = s.charAt(endIndex);
            if (charToIndex.containsKey(endChar)) {
                int lastIndex = charToIndex.get(endChar);
                startIndex = Math.max(lastIndex + 1, startIndex);
            }
            res = Math.max(res, endIndex - startIndex + 1);
            charToIndex.put(endChar, endIndex);
        }
        return res;
    }

    public static int
    lengthOfLongestSubstringE3(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }
        Integer[] charIndexes = new Integer[128];
        int left = 0;
        int right = 0;

        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            Integer index = charIndexes[r];
            if (index != null) {
                int idx = index.intValue();
                if (idx >= left && idx < right) {
                    left = idx + 1;
                }
            }
            res = Math.max(res, right - left + 1);
            charIndexes[r] = right;
            ++right;
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "abcabcbb";

        System.out.println("Brute-force method:");
        int maxLen = lengthOfLongestSubstring(str);
        System.out.println("Length of longest substring : " + maxLen);

        System.out.println("Efficient method:");
        maxLen = lengthOfLongestSubstringE1(str);
        System.out.println("Length of longest substring : " + maxLen);

        System.out.println("More efficient method:");
        maxLen = lengthOfLongestSubstringE2(str);
        System.out.println("Length of longest substring : " + maxLen);

        System.out.println("One more efficient method:");
        maxLen = lengthOfLongestSubstringE3(str);
        System.out.println("Length of longest substring : " + maxLen);
    }
}
