package com.algo.lc.monotonicqueue.continuoussubarray;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class ContinuousSubarray {
    public static long continuousSubarraySortedMap(int[] nums) {
        long count = 0;
        int left = 0, right = 0;
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        while (right < nums.length) {
            int num = nums[right];
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            while (freqMap.lastKey() - freqMap.firstKey() > 2) {
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                ++left;
            }
            count += right - left + 1;
            ++right;
        }
        return count;
    }

    public static long continuousSubarrayPQ(int[] nums) {
        int left = 0, right = 0;
        int count = 0;
        // Use minHeap and maxHeap simultaneously
        Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);

        while (right < nums.length) {
            minHeap.offer(right);
            maxHeap.offer(right);

            while (left < right && nums[maxHeap.peek()] - nums[minHeap.peek()] > 2) {
                ++left;
                while (!maxHeap.isEmpty() && maxHeap.peek() < left) {
                    maxHeap.poll();
                }

                while (!minHeap.isEmpty() && minHeap.peek() < left) {
                    minHeap.poll();
                }
            }

            // Add count of all valid subarrays ending at right
            count += right - left + 1;
            ++right;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 3, 2, 3, 1};
        long numSubarrays = continuousSubarraySortedMap(nums);
        System.out.println("Number of subarrays (sorted map): " + numSubarrays);

        numSubarrays = continuousSubarraySortedMap(nums);
        System.out.println("Number of subarrays (Priority queue): " + numSubarrays);
    }
}
