package com.algo.lc.treegraphs.bit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountSmallerThanSelf {
    public List<Integer> countSmaller(int[] nums) {
        int offset = 10000; // offset negative to non-negative
        int size = 2 * 10000 + 2; // total possible values in nums plus one dummy
        int[] tree = new int[size];
        List<Integer> result = new ArrayList<Integer>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int smaller_count = query(nums[i] + offset, tree);
            result.add(smaller_count);
            update(nums[i] + offset, 1, tree, size);
        }
        Collections.reverse(result);
        return result;
    }

    // implement Binary Index Tree
    private void update(int index, int value, int[] tree, int size) {
        index++; // index in BIT is 1 more than the original index
        while (index < size) {
            tree[index] += value;
            index += index & -index;
        }
    }

    private int query(int index, int[] tree) {
        // return sum of [0, index)
        int result = 0;
        while (index >= 1) {
            result += tree[index];
            index -= index & -index;
        }
        return result;
    }

    public static void main(String[] args) {
        CountSmallerThanSelf obj = new CountSmallerThanSelf();
        int [] nums = {5, 2, 6, 1};
        List<Integer> smallerCounts = obj.countSmaller(nums);
        System.out.println(smallerCounts.toString());
    }
}
