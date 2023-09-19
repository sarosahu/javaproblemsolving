package com.algo.lc.recursion.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 *
 *
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= n - k; ++i) {
            int firstNum = i + 1;
            List<Integer> currList = new ArrayList<>();
            currList.add(firstNum);
            combineHelper(n, k, currList, result);
        }
        return result;
    }

    /**
     *
     * @param n
     * @param k
     * @param currList
     * @param result
     * Note: This is a recursive backtracking implementation.
     *
     * Time complexity: O(n!/(k−1)!⋅(n−k)!)
     *
     * Space complexity: O(k)
     *
     * We don't count the answer as part of the space complexity. The extra space we use
     * here is for curr and the recursion call stack. The depth of the call stack is equal
     * to the length of curr, which is limited to k.
     */
    public void combineHelper(int n, int k,
                              List<Integer> currList,
                              List<List<Integer>> result) {
        if (currList.size() == k) {
            result.add(new ArrayList<>(currList));
            return;
        }
        int nextNum = currList.get(currList.size() - 1) + 1;
        for (int num = nextNum; num <= n; ++num) {
            currList.add(num);
            combineHelper(n, k, currList, result);
            currList.remove(currList.size() - 1);
        }
    }
}
