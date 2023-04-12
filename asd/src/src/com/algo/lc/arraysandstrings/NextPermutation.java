package com.algo.lc.arraysandstrings;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int pivot = -1;
        int right = nums.length - 1;
        while (right > 0 && nums[right] <= nums[right - 1]) {
            --right;
        }
        if (right == 0) {
            doReverse(nums, 0, nums.length - 1);
        } else {
            pivot = right - 1;
        }

        if (pivot != -1) {
            int lastIdx = nums.length - 1;
            while (lastIdx > pivot) {
                if (nums[pivot] < nums[lastIdx]) {
                    doSwap(nums, pivot, lastIdx);
                    break;
                }
                --lastIdx;
            }
            // Reverse all the elements from pivot + 1 to last index elements
            doReverse(nums, pivot + 1, nums.length - 1);
        }
    }

    public void doSwap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void doReverse(int[] arr, int left, int right) {
        while (left < right) {
            doSwap(arr, left, right);
            ++left;
            --right;
        }
    }
}
