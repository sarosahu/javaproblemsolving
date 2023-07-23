package com.algo.lc.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Delete and Earn
 *
 * You are given an integer array nums. You want to maximize the number of points you get by
 * performing the following operation any number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every
 * element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 *
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 * Example 1:
 *
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 *
 *
 * Example 2:
 *
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class DeleteAndEarn {
    private Map<Integer, Integer> points = new HashMap<>();
    private Map<Integer, Integer> cache = new HashMap<>();

    /**
     * Approach 1: Top-Down Dynamic Programming
     *
     * Complexity Analysis
     *
     * Given NNN as the length of nums and kkk as the maximum element in nums,
     *
     * Time complexity: O(N + k)
     *
     * To populate points, we need to iterate through nums once, which costs O(N) time.
     * Then, we call maxPoints(maxNumber). This call will repeatedly call maxPoints until
     * we get down to our base cases. Because of cache, already solved sub-problems will
     * only cost O(1) time. Since maxNumber = k, we will solve k unique sub-problems so,
     * this recursion will cost O(k) time. Our final time complexity is O(N + k).
     *
     * Space complexity: O(N + k)
     *
     * The extra space we use is the hash table points, the recursion call stack needed to
     * find maxPoints(maxNumber), and the hash table cache.
     *
     * The size of points is equal to the number of unique elements in nums. In the worst case,
     * where every element in nums is unique, this will take O(N) space. The recursion call
     * stack will also grow up to size k, since we start our recursion at maxNumber, and we
     * don't start returning values until our base cases at 0 and 1. Lastly, cache will store
     * the answer for all states, from 2 to maxNumber, which means it also grows up to k size.
     * Our final space complexity is O(N+2⋅k) = O(N + k).
     */
    public int deleteAndEarnTopDown(int[] nums) {
        int maxNum = 0;

        // Precompute how many points we gain from taking an element
        for(int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNum = Math.max(maxNum, num);
        }

        return maxPoints(maxNum);
    }

    /**
     * maxPoints(num) returns the maximum points that we can gain if we
     * only consider all the elements in nums with values between 0 and num.
     */
    private int maxPoints(int num) {
        // Check for base cases
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return points.getOrDefault(1, 0);
        }
        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        // Apply recurrence relation
        int gain = points.getOrDefault(num, 0);
        int maxPointsWithoutGain = maxPoints(num - 1);
        int maxPointsWithGain = maxPoints(num - 2) + gain;
        int maxPointsHere = Math.max(maxPointsWithoutGain, maxPointsWithGain);
        cache.put(num, maxPointsHere);

        return cache.get(num);
    }

    /**
     *
     * Approach 2 : Bottom up dynamic programming
     *
     * Complexity Analysis
     *
     * Given NNN as the length of nums and kkk as the maximum element in nums,
     *
     * Time complexity: O(N+k)
     *
     * To populate points, we need to iterate through nums once, which costs O(N) time. Then,
     * we populate maxPoints by iterating over it. The length of maxPoints is equal to k + 1,
     * so this will cost O(k) time. Our final time complexity is O(N+k).
     *
     * Space complexity: O(N + k)
     *
     * The extra space we use is the hash table points and our DP array maxPoints. The size of
     * maxPoints is equal to k + 1, which means it takes O(k) space. The size of points is equal
     * to the number of unique elements in nums. In the worst case, where every element in nums
     * is unique, this will take O(N) space. Our final space complexity is O(N+k).
     */
    public int deleteAndEarnBottomUp(int[] nums) {
        Map<Integer, Integer> points = new HashMap<>();
        int maxNum = 0;

        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNum = Math.max(maxNum, num);
        }

        // Declare our array along with bases cases
        int [] maxPoints = new int[maxNum + 1];
        maxPoints[1] = points.getOrDefault(1, 0);

        for (int num = 2; num < maxPoints.length; ++num) {
            int gain = points.getOrDefault(num, 0);
            maxPoints[num] = Math.max(maxPoints[num - 1], maxPoints[num - 2] + gain);
        }

        return maxPoints[maxNum];
    }

    /**
     * Complexity Analysis
     *
     * Given N as the length of nums and kkk as the maximum element in nums,
     *
     * Time complexity: O(N+k)
     *
     * To populate points, we need to iterate through nums once, which costs O(N) time.
     * Then, we iterate from 2 to k, doing O(1) work at each iteration, so this will cost O(k)
     * time. Our final time complexity is O(N+k).
     *
     * Space complexity: O(N)
     *
     * The extra space we use is the hash table points.
     *
     * The size of points is equal to the number of unique elements in nums. In the worst case,
     * where every element in nums is unique, this will take O(N) space.
     *
     * Unlike in approach 2, we no longer need an array maxPoints which would be of size k.
     * Thus, we have improved the space complexity to O(N).
     */
    public int deleteAndEarnBottomUpOptimized(int[] nums) {
        Map<Integer, Integer> points = new HashMap<>();
        int maxNum = 0;

        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNum = Math.max(maxNum, num);
        }

        // Bases cases
        int twoBack = 0;
        int oneBack = points.getOrDefault(1, 0);

        for (int num = 2; num <= maxNum; ++num) {
            int gain = points.getOrDefault(num, 0);
            int temp = oneBack;
            oneBack = Math.max(oneBack, twoBack + gain);
            twoBack = temp;
        }

        return oneBack;
    }

    // Approach 4: Sorting with keys (stored in map of num to total points)

    /**
     * Complexity Analysis
     *
     * Given N as the length of nums,
     *
     * Time complexity: O(N⋅log(N))
     *
     * To populate points, we need to iterate through nums once, which costs O(N) time.
     *
     * Next, we take all the keys of points and sort them to create elements. In the worst case
     * when nums only contains unique elements, there will be N keys, which means this will cost
     * O(N⋅log(N)) time.
     *
     * Lastly, we iterate through elements, which again in the worst case costs O(N) time when
     * all the elements are unique.
     *
     * This gives us a time complexity of O(N+N⋅log(N)+N)=O(N⋅log(N)).
     *
     * Space complexity: O(N)
     *
     * The extra space we use is the hash table points and elements. These have the same length,
     * and in the worst case scenario when nums only contains unique elements, their lengths will
     * be equal to N.
     */
    public int deleteAndEarnWithSorting(int[] nums) {
        Map<Integer, Integer> points = new HashMap<>();

        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
        }
        List<Integer> elements = new ArrayList<>(points.keySet());
        Collections.sort(elements);

        // Base cases
        int twoBack = 0;
        int oneBack = points.get(elements.get(0));

        for (int i = 1; i < elements.size(); ++i) {
            int curr = elements.get(i);
            int temp = oneBack;
            if (curr == elements.get(i - 1) + 1) {
                // The 2 elements are adjacent, can't take both -- apply normal recurrence
                oneBack = Math.max(oneBack, twoBack + points.get(curr));
            } else {
                // Otherwise, we don't need to worry about adjacent deletions
                oneBack += points.get(curr);
            }
            twoBack = temp;
        }

        return oneBack;
    }
    public static void main(String[] args) {
        DeleteAndEarn obj = new DeleteAndEarn();
        int[] nums = {2, 2, 3, 3, 3, 4};
        int maxPoint = obj.deleteAndEarnTopDown(nums);
        System.out.println("Max point : " + maxPoint);

        maxPoint = obj.deleteAndEarnBottomUp(nums);
        System.out.println("Max point : " + maxPoint);

        maxPoint = obj.deleteAndEarnBottomUpOptimized(nums);
        System.out.println("Max point : " + maxPoint);

        maxPoint = obj.deleteAndEarnWithSorting(nums);
        System.out.println("Max point : " + maxPoint);
    }
}
