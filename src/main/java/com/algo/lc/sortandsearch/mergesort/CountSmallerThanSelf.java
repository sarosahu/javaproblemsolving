package com.algo.lc.sortandsearch.mergesort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CountSmallerThanSelf {
    private int[] nums;
    public CountSmallerThanSelf(int[] nums) {
        this.nums = nums;
    }
    public List<Integer> countSmaller() {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int n = nums.length;
        int [] result = new int[n];
        ItemValWithIndex[] newNums = new ItemValWithIndex[n];
        for (int i = 0; i < n; ++i) {
            newNums[i] = new ItemValWithIndex(nums[i], i);
        }

        mergeSortAndCount(newNums, 0, n - 1, result);

        List<Integer> resultList = new ArrayList<>();
        for (int i : result) {
            resultList.add(i);
        }
        return resultList;
    }

    private void mergeSortAndCount(ItemValWithIndex[] nums, int left, int right, int[] result) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSortAndCount(nums, left, mid, result);
        mergeSortAndCount(nums, mid + 1, right, result);
        doMerge(nums, left, right, mid, result);
    }

    private void doMerge(ItemValWithIndex[] nums, int start, int end, int mid, int[] result) {
        int leftIter = start, rightIter = mid + 1;
        LinkedList<ItemValWithIndex> merged = new LinkedList<>();
        int itemFromRightArrayLesserCount = 0;
        while (leftIter <= mid && rightIter <= end) {
            if (nums[leftIter].val > nums[rightIter].val) {
                ++itemFromRightArrayLesserCount;
                merged.add(nums[rightIter]);
                ++rightIter;
            } else {
                result[nums[leftIter].idx] += itemFromRightArrayLesserCount;
                merged.add(nums[leftIter]);
                ++leftIter;
            }
        }
        while (leftIter <= mid) {
            result[nums[leftIter].idx] += itemFromRightArrayLesserCount;
            merged.add(nums[leftIter]);
            ++leftIter;
        }
        while (rightIter <= end) {
            merged.add(nums[rightIter]);
            ++rightIter;
        }

        int pos = start;
        for (ItemValWithIndex i : merged) {
            nums[pos++] = i;
        }
    }

    static class ItemValWithIndex {
        int val;
        int idx;
        public ItemValWithIndex(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public static void main(String[] args) {
        int [] nums = {5, 2, 6, 1};
        CountSmallerThanSelf obj = new CountSmallerThanSelf(nums);
        List<Integer> rightSmallerCounts = obj.countSmaller();
        System.out.println("Smaller counts list : " + rightSmallerCounts.toString());
    }
}
