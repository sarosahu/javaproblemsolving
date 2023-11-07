package com.algo.lc.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * >>>>>>>>>>>>>>>>>>>>
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 *
 * Constraints:
 *
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class NthTribonacci {
    private Map<Integer, Integer> dp = new HashMap<>() {{
        put(0, 0);
        put(1, 1);
        put(2, 1);
    }};

    // Top-down approach(recursion)
    // Time: O(N), space: O(N)
    public int tribonacci(int n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int ans = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
        dp.put(n, ans);
        return ans;
    }

    // Bottom-up approach (iteration)
    // Time: O(N), space: O(N)
    public int tribonacciBU(int n) {
        if (n <= 2) {
            return n <= 1 ? n : 1;
        }
        int t[] = new int[n + 1];
        t[0] = 0;
        t[1] = 1;
        t[2] = 1;
        for (int i = 3; i <= n; ++i) {
            t[i] = t[i-1] + t[i-2] + t[i-3];
        }
        return t[n];
    }

    // Bottom-up approach (Optimized space)
    // Time: O(N), space: O(1)
    public int tribonacciBUOptimized(int n) {
        if (n <= 2) {
            return n <= 1 ? n : 1;
        }
        int t0 = 0, t1 = 1, t2 = 1;
        for (int i = 3; i <= n; ++i) {
            int t3 = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = t3;
        }
        return t2;
    }

    public static void main(String[] args) {
        NthTribonacci obj = new NthTribonacci();
        int tn = obj.tribonacci(10);
        System.out.println("10th tribonacci number : " + tn);

        tn = obj.tribonacciBU(10);
        System.out.println("10th tribonacci number : " + tn);

        tn = obj.tribonacciBUOptimized(10);
        System.out.println("10th tribonacci number : " + tn);
    }
}
