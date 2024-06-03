package com.algo.lc.queue;

import com.algo.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumAtleastK {
    public int shortestSubarray(int[] nums, int k) {
        int minLen = Integer.MAX_VALUE;
        long sum = 0;
        // Deque element is a pair of sum and index.
        Deque<Pair<Long, Integer>> deque = new ArrayDeque<>();
        // Initially the the sum = 0 and starting index is -1 (prior first index)
        deque.add(new Pair<>(0L, -1));
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] >= k) {
                return 1;
            }
            sum += nums[i];
            if (sum >= k) {
                minLen = Math.min(minLen, i + 1);
            }
            while (!deque.isEmpty() && (sum - deque.peekFirst().getKey()) >= k) {
                Pair<Long, Integer> curr = deque.pollFirst();
                minLen = Math.min(minLen, i - curr.getValue());
            }

            while (!deque.isEmpty() && sum <= deque.peekLast().getKey()) {
                deque.pollLast();
            }

            deque.add(new Pair<>(sum, i));
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    public static void main(String[] args) {
        int [] nums = {-10, 5, 2, 1, 6, -5, 6, 3, 1, };
        ShortestSubarrayWithSumAtleastK obj = new ShortestSubarrayWithSumAtleastK();
        int minLen = obj.shortestSubarray(nums, 10);
        System.out.println("Shortest subarray length : " + minLen);
    }
}
