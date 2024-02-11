package com.algo.lc.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 276. Paint Fence
 *
 * You are painting a fence of n posts with k different colors. You must paint
 * the posts following these rules:
 *
 * - Every post must be painted exactly one color.
 * - There cannot be three or more consecutive posts with the same color.
 *
 * Given the two integers n and k, return the number of ways you can paint the fence.
 *
 * Example 1:
 * =========
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: All the possibilities are shown. (Assume k=1 is red, k=2 is green)
 * Note that painting all the posts red or all the posts green is invalid because
 * there cannot be three posts in a row with the same color.
 *
 * Example 2:
 * =========
 * Input: n = 1, k = 1
 * Output: 1
 *
 *
 * Example 3:
 * =========
 * Input: n = 7, k = 2
 * Output: 42
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * 1 <= k <= 105
 * The testcases are generated such that the answer is in the range [0, 2^31 - 1] for the given n and k.
 *
 */
public class PaintFence {
    // { Top-down recursion with memoization
    public int numWaysTD(int n, int k) {
        Map<Integer, Integer> memo = new HashMap<>();
        return numWays(n, k, memo);
    }

    private int numWays(int i, int k, Map<Integer, Integer> memo) {
        if (i == 1) {
            return k;
        }
        if (i == 2) {
            return k * k;
        }

        // Check memo
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        // Use recurrence relation
        int totalWays = numWays(i - 1, k, memo) + numWays(i - 2, k, memo);
        memo.put(i, (k - 1) * totalWays);

        return memo.get(i);
    }
    // }
    // { Bottom-up
    public int numWaysBU(int n, int k) {
        // Base cases
        if (n == 1) {
            return k;
        }
        if (n == 2) {
            return k * k;
        }

        int [] totalWays = new int[n + 1];
        totalWays[1] = k;
        totalWays[2] = k * k;

        for (int i = 3; i <= n; ++i) {
            totalWays[i] = (k - 1) * (totalWays[i - 1] + totalWays[i - 2]);
        }
        return totalWays[n];
    }
    // }

    // { Constant space:
    public int numWaysO(int n, int k) {
        if (n == 1) return k;

        int twoPostsBack = k;
        int onePostBack = k * k;

        for (int i = 3; i <= n; i++) {
            int curr = (k - 1) * (onePostBack + twoPostsBack);
            twoPostsBack = onePostBack;
            onePostBack = curr;
        }

        return onePostBack;
    }
    // }

    public static void main(String[] args) {
        PaintFence paintFence = new PaintFence();

        int nways = paintFence.numWaysTD(3, 2);
        System.out.println("Total number of ways : " + nways);

        nways = paintFence.numWaysBU(3, 2);
        System.out.println("Total number of ways : " + nways);

        nways = paintFence.numWaysO(3, 2);
        System.out.println("Total number of ways : " + nways);
    }
}
