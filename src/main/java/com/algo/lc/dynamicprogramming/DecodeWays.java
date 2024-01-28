package com.algo.lc.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 91. Decode Ways
 *
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters
 * using the reverse of the mapping above (there may be multiple ways). For example, "11106"
 * can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6"
 * is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {
    private Map<Integer, Integer> memo = new HashMap<>();
    public int numDecodingsR(String s) {
        return numDecodingHelper(0, s);
    }

    // Recursive approach
    // Time: O(N), space : O(N)
    public int numDecodingHelper(int index, String s) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        if (index == s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }
        if (index == s.length() - 1) {
            return 1;
        }
        int ans = 0;
        ans += numDecodingHelper(index + 1, s);
        int currentTwoDigitNum = Integer.parseInt(s.substring(index, index + 2));
        if (currentTwoDigitNum <= 26) {
            ans += numDecodingHelper(index + 2, s);
        }
        memo.put(index, ans);
        return ans;
    }

    // Iterative approach.
    // Time: O(N), space : O(N)
    public int numDecodings(String s) {
        // DP array to store the subproblem results
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i < dp.length; ++i) {
            // Check if successful single digit decode is possible
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            int twoDigitNum = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigitNum >= 10 && twoDigitNum <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }

    // Iterative approach
    // Time: O(N), space O(1)
    public int numDecodingsOptimized(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int twoBack = 1;
        int oneBack = 1;
        for (int i = 1; i < s.length(); ++i) {
            int curr = 0;
            if (s.charAt(i) != '0') {
                curr = oneBack;
            }
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                curr += twoBack;
            }
            twoBack = oneBack;
            oneBack = curr;
        }
        return oneBack;
    }

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        String s = "232066102";
        int numWays = decodeWays.numDecodingsR(s);
        System.out.println("No of ways : " + numWays);
        numWays = decodeWays.numDecodings(s);
        System.out.println("No of ways : " + numWays);
        numWays = decodeWays.numDecodingsOptimized(s);
        System.out.println("No of ways : " + numWays);
    }
}
