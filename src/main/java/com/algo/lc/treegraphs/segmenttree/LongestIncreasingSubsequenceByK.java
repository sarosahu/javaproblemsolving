package com.algo.lc.treegraphs.segmenttree;

/**
 * You are given an integer array nums and an integer k.
 *
 * Find the longest subsequence of nums that meets the following requirements:
 *
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * Return the length of the longest subsequence that meets the requirements.
 *
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 *
 * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
 * Output: 5
 * Explanation:
 * The longest subsequence that meets the requirements is [1,3,4,5,8].
 * The subsequence has a length of 5, so we return 5.
 * Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.
 *
 *
 * Example 2:
 *
 * Input: nums = [7,4,5,1,8,12,4,7], k = 5
 * Output: 4
 * Explanation:
 * The longest subsequence that meets the requirements is [4,5,8,12].
 * The subsequence has a length of 4, so we return 4.
 *
 *
 * Example 3:
 *
 * Input: nums = [1,5], k = 1
 * Output: 1
 * Explanation:
 * The longest subsequence that meets the requirements is [1].
 * The subsequence has a length of 1, so we return 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i], k <= 10^5
 */
public class LongestIncreasingSubsequenceByK {
    static class Node {
        Node leftChild;
        Node rightChild;
        int start;
        int end;
        int value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }

    private static Node buildSegmentTree(int start, int end) {
        if (start == end)
            return new Node(start, end, 0);
        Node node = new Node(start, end, 0);
        int mid = start + (end - start) / 2;
        node.leftChild = buildSegmentTree(start, mid);
        node.rightChild = buildSegmentTree(mid + 1, end);
        return node;
    }

    private static int queryRangeMax(Node node, int l, int r) {
        if (node == null || l > node.end || r < node.start)
            return 0; // Return 0 for out-of-bound queries
        if (l <= node.start && r >= node.end)
            return node.value; // Total overlap
        // Partial overlap
        return Math.max(queryRangeMax(node.leftChild, l, r), queryRangeMax(node.rightChild, l, r));
    }

    private static void updateSegmentTree(Node node, int index, int value) {
        if (node == null || index < node.start || index > node.end)
            return; // Out of bounds
        node.value = Math.max(value, node.value); // Update the current node
        if (node.start != node.end) { // If it's not a leaf node
            updateSegmentTree(node.leftChild, index, value);
            updateSegmentTree(node.rightChild, index, value);
        }
    }


    public static int lengthOfLIS(int[] nums, int k) {
        //Node root = buildSegmentTree(0, 100000);
        Node root = buildSegmentTree(0, 15);
        int ans = 1;
        for (int num : nums) {
            int maxValInRange = queryRangeMax(root, Math.max(0, num - k), num - 1) + 1;
            ans = Math.max(ans, maxValInRange);
            updateSegmentTree(root, num, maxValInRange);
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] nums = {4,2,1,4,3,4,5,8,15};
        int k = 3;

        System.out.println("Length of LIS with difference (" + k + ") :" + lengthOfLIS(nums, k));
    }
}
