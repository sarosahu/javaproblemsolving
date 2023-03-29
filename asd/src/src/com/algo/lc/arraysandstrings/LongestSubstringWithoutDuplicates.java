package com.algo.lc.arraysandstrings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutDuplicates {
    /**
     * Complexity Analysis
     * Time complexity : O(2n)=O(n). In the worst case each character will be visited twice by i and j.
     *
     * Space complexity : O(min(m,n)). We need O(k) space for the sliding window, where k is the size of the Set.
     * The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charToCount = new HashMap<>();
        int left = 0, right = 0;
        int maxLen = 0;

        while (right < s.length()) {
            char r = s.charAt(right);
            int rCount = charToCount.getOrDefault(r, 0);
            charToCount.put(r, rCount + 1);

            while (charToCount.get(r) > 1) {
                char l = s.charAt(left);
                int lCount = charToCount.get(l);
                charToCount.put(l, lCount - 1);
                ++left;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            ++right;
        }
        return maxLen;
    }

    // Optimized version

    /**
     * Complexity Analysis
     * Time complexity : O(n). Index j will iterate n times.
     * Space complexity : O(min(m,n)). Same as the previous approach.
     */
    public static int lengthOfLongestSubstringO(String s) {
        int ans = 0;
        // Store char to current index
        Map<Character, Integer> map = new HashMap<>();
        // Try to expand the range [i, j]
        for (int j = 0, i = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                int lastIdx = map.get(c);
                i = Math.max(i, lastIdx + 1);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(c, j);
        }
        return ans;
    }

    // Another optimized version
    public static int lengthOfLongestSubstringO2(String s) {
        int left = 0, right = 0;
        int res = 0;
        Integer [] chars = new Integer[128];

        while (right < s.length()) {
            char r = s.charAt(right);
            Integer lastIndex = chars[r];
            if (lastIndex != null && lastIndex >= left && lastIndex < right) {
                left = lastIndex + 1;
            }
            res = Math.max(res, right - left + 1);
            chars[r] = right;
            ++right;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        int len = lengthOfLongestSubstring(s);
        System.out.println("Longest substring length : " + len);

        len = lengthOfLongestSubstringO(s);
        System.out.println("Longest substring length (optimized): " + len);

        len = lengthOfLongestSubstringO2(s);
        System.out.println("Longest substring length (optimized 2): " + len);
    }
}
