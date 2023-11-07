package com.algo.lc.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array cost where cost[i] is the cost of ith step
 * on a staircase. Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 * Example 1:
 *
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 *
 *
 * Example 2:
 *
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 *
 * Constraints:
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class MinCostClimbingStairs {
    private int[] cost;
    private Map<Integer, Integer> memo;
    /**
     * Bottom-up approach (iteration/tabulation)
     * Complexity Analysis
     *
     * Given N as the length of cost,
     *
     * Time complexity: O(N).
     *
     * We iterate N - 1 times, and at each iteration we apply an equation that
     * requires O(1) time.
     *
     * Space complexity: O(N).
     *
     * The array minimumCost is always 1 element longer than the array cost.
     */
    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        // To reach first 2 steps, it's free, no cost is required.
        if (cost.length == 2) {
            return 0;
        }
        // The array's length should be 1 longer than the length of cost.
        // This is because we can treat the "top floor" as a step to reach.
        int minCost[] = new int[cost.length + 1];
        minCost[0] = 0;
        minCost[1] = 0;

        // Start iteration from step 2, since minimum cost of reaching
        // step 0 and step 1 is 0.
        for (int i = 2; i < minCost.length; ++i) {
            int takeOneStep = minCost[i - 1] + cost[i - 1];
            int takeTwoSteps = minCost[i - 2] + cost[i - 2];
            minCost[i] = Math.min(takeOneStep, takeTwoSteps);
        }

        // The final element in minCost refers to the top floor
        return minCost[minCost.length - 1];
    }

    /**
     * Top-down approach (recursion approach)
     *
     * Complexity Analysis
     *
     * Given N as the length of cost,
     *
     * Time complexity: O(N)
     *
     * minimumCost gets called with each index from 0 to N. Because of our memoization,
     * each call will only take O(1) time.
     *
     * Space complexity: O(N)
     *
     * The extra space used by this algorithm is the recursion call stack. For example,
     * minimumCost(10000) will call minimumCost(9999), which calls minimumCost(9998) etc.,
     * all the way down until the base cases at minimumCost(0) and minimumCost(1).
     * In addition, our hash map memo will be of size N at the end, since we populate
     * it with every index from 0 to N.
     */
    public int minCostClimbingStairs2(int[] cost) {
        this.cost = cost;
        memo = new HashMap<>();
        return minCostTopDown(cost.length);
    }
    public int minCostTopDown(int pos) {
        // Base case: we are allowed to start at either step 0 or step 1
        // So minimum cost required to reach either step 0 or step 1 is 0.
        if (pos <= 1) {
            return 0;
        }

        // Check if we have already calculated minimum cost at position 'pos'
        if (!memo.containsKey(pos)) {
            int downOne = cost[pos - 1] + minCostTopDown(pos - 1);
            int downTwo = cost[pos - 2] + minCostTopDown(pos - 2);
            memo.put(pos, Math.min(downOne, downTwo));
        }
        return memo.get(pos);
    }

    /**
     * Approach 3: Bottom-up, constant space.
     *
     * Complexity Analysis
     *
     * Given N as the length of cost,
     *
     * Time complexity: O(N).
     *
     * We only iterate N - 1 times, and at each iteration we apply an equation that uses O(1) time.
     *
     * Space complexity: O(1)
     *
     * The only extra space we use is 2 variables, which are independent of input size.
     */
    public int minCostClimbingStairsBottomUpOptimized(int[] cost) {
        this.cost = cost;
        int downOne = 0;
        int downTwo = 0;
        for (int i = 2; i <= cost.length; ++i) {
            int temp = downOne;
            downOne = Math.min(downOne + cost[i - 1], downTwo + cost[i - 2]);
            downTwo = temp;
        }
        return downOne;
    }

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        MinCostClimbingStairs obj = new MinCostClimbingStairs();
        int minCost = obj.minCostClimbingStairs(cost);
        System.out.println("Minimum cost to climb top stair : " + minCost);

        minCost = obj.minCostClimbingStairs2(cost);
        System.out.println("Minimum cost to climb top stair : " + minCost);

        minCost = obj.minCostClimbingStairsBottomUpOptimized(cost);
        System.out.println("Minimum cost to climb top stair : " + minCost);
    }
}
