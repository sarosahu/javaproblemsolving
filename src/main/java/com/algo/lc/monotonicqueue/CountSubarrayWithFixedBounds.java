package com.algo.lc.monotonicqueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 2444. Count Subarrays With Fixed Bounds
 *
 * You are given an integer array nums and two integers minK and maxK.
 *
 * A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
 *
 * The minimum value in the subarray is equal to minK.
 * The maximum value in the subarray is equal to maxK.
 * Return the number of fixed-bound subarrays.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
 * Output: 2
 * Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].
 *
 * Example 2:
 *
 * Input: nums = [1,1,1,1], minK = 1, maxK = 1
 * Output: 10
 * Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i], minK, maxK <= 106
 */
public class CountSubarrayWithFixedBounds {
    //TODO: Not working -- needs to change
    public static long countSubarrays(int[] nums, int minK, int maxK) {
        Deque<Integer> minQ = new LinkedList<>();
        Deque<Integer> maxQ = new LinkedList<>();
        int cnt = 0;

        for (int i = 0; i < nums.length; ++i) {
            int curr = nums[i];
            if (minQ.isEmpty() && maxQ.isEmpty()) {
                minQ.offerLast(curr);
                maxQ.offerLast(curr);
            } else {
                if (minQ.isEmpty() || (minQ.peekLast() < curr)) {
                    minQ.offerLast(curr);
                } else {
                    while (!minQ.isEmpty() && curr < minQ.peekLast()) {
                        minQ.pollLast();
                    }
                    minQ.offerLast(curr);
                }
                if (maxQ.isEmpty() || (maxQ.peekLast() > curr)) {
                    maxQ.offerLast(curr);
                } else {
                    while (!maxQ.isEmpty() && curr > maxQ.peekLast()) {
                        maxQ.pollLast();
                    }
                    maxQ.offerLast(curr);
                }
            }
            if (minQ.peekFirst() == minK && maxQ.peekFirst() == maxK) {
                ++cnt;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int [] nums = {1,3,5,2,7,5};
        int minK = 1, maxK = 5;
        long cnt = countSubarrays(nums, minK, maxK);
        System.out.println("Count of subarrays : " + cnt);
    }
}
