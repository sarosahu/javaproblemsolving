package com.algo.lc.trie;

/**
 *
 * 421. Maximum XOR of Two Numbers in an Array
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j],
 * where 0 <= i <= j < n.
 *
 * Example 1:
 *
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 *
 * Example 2:
 *
 * Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * Output: 127
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1
 */
public class MaximumXORTwoNumbersInArray {
    public int findMaximumXOR(int[] nums) {
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int L = (Integer.toBinaryString(maxNum)).length();
        int max = 0;
        TrieNode root = new TrieNode();

        for (int num : nums) {
            TrieNode curr = root, xorNode = root;
            int currXor = 0;

            for (int i = L - 1; i >= 0; --i) {
                int currBit = (num >> i) & 1;
                int toggleBit = currBit ^ 1;

                if (curr.children[currBit] == null) {
                    curr.children[currBit] = new TrieNode();
                }
                curr = curr.children[currBit];

                if (xorNode.children[toggleBit] != null) {
                    currXor |= (1 << i);
                    xorNode = xorNode.children[toggleBit];
                } else {
                    xorNode = xorNode.children[currBit];
                }
            }
            max = Math.max(max, currXor);
        }
        return max;
    }

    public int findMaximumXOR2(int[] nums) {
        int maxNum = nums[0];
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        int L = (Integer.toBinaryString(maxNum)).length();

        Trie trie = new Trie();
        trie.insert(nums, L);
        int max = 0;

        for (int num : nums) {
            TrieNode curr = trie.root;
            int currSum = 0;
            for (int i = L - 1; i >= 0; --i) {
                int currBit = (num >> i) & 1;
                // if currBit is 0, we need 1 and if currBit is 1, we need 0. Thus, 1 - currBit
                int requiredBit = 1 - currBit;
                if (curr.children[requiredBit] != null) {
                    currSum |= (1 << i);  // set ith bit of curr result;
                    curr = curr.children[requiredBit];
                } else {
                    curr = curr.children[currBit];
                }
            }
            max = Math.max(max, currSum);
        }
        return max;
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }
    static class Trie {
        TrieNode root = new TrieNode();

        public void insert(int[] nums, int L) {
            for (int num : nums) {
                TrieNode curr = root;
                for (int i = L - 1; i >= 0; --i) {
                    int currBit = (num >> i) & 1;
                    if (curr.children[currBit] == null) {
                        curr.children[currBit] = new TrieNode();
                    }
                    curr = curr.children[currBit];
                }
            }
        }
    }

    public static void main(String[] args) {
        MaximumXORTwoNumbersInArray obj = new MaximumXORTwoNumbersInArray();
        int [] arr = {3,10,5,25,2,8};
        int maxXor = obj.findMaximumXOR(arr);
        System.out.println("Maximum XOR : " + maxXor);

        maxXor = obj.findMaximumXOR2(arr);
        System.out.println("Maximum XOR : " + maxXor);

        int[] arr2 = {14,70,53,83,49,91,36,80,92,51,66,70};
        maxXor = obj.findMaximumXOR(arr2);
        System.out.println("Maximum XOR : " + maxXor);
        maxXor = obj.findMaximumXOR2(arr2);
        System.out.println("Maximum XOR : " + maxXor);
    }
}
