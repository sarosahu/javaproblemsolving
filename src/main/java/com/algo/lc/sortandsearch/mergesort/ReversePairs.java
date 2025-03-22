package com.algo.lc.sortandsearch.mergesort;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        // Brute force approach
        // Timelimit exceeded.
        /*
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            long first = nums[i];
            for (int j = i + 1; j < nums.length; ++j) {
                long second = nums[j];
                second *= 2;
                if (first > second) {
                    ++cnt;
                }
            }
        }
        return cnt;
        */

        // Merge sort approach.
        int [] tmpArr = new int[nums.length];
        return reversePairsHelper(nums, 0, nums.length - 1, tmpArr);
    }

    private int reversePairsHelper(int[] arr, int left, int right, int [] tmpArr) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int lc = reversePairsHelper(arr, left, mid, tmpArr);
        int rc = reversePairsHelper(arr, mid + 1, right, tmpArr);
        int cnt = countReversePair(arr, left, right, mid);

        merge(arr, left, right, mid, tmpArr);

        return lc + rc + cnt;
    }

    private int countReversePair(int[] arr, int left, int right, int mid) {
        int i = left, j = mid + 1;
        int cnt = 0;
        while (i <= mid && j <= right) {
            if (arr[i]/2.0 > arr[j]) {
                cnt += mid - i + 1;
                ++j;
            } else {
                ++i;
            }
        }
        return cnt;
    }

    private void merge(int[] arr, int left, int right, int mid, int[] tmpArr) {
        // Copy existing arr to tmpArr
        copyArray(arr, tmpArr, left, mid);
        copyArray(arr, tmpArr, mid + 1, right);

        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            long first = tmpArr[i];
            long second = tmpArr[j];
            if (first <= second) {
                arr[k++] = tmpArr[i++];
            } else {
                arr[k++] = tmpArr[j++];
            }
        }

        while (i <= mid) {
            arr[k++] = tmpArr[i++];
        }
        while (j <= right) {
            arr[k++] = tmpArr[j++];
        }
    }

    private void copyArray(int[] src, int [] dest, int fromIdx, int toIdx) {
        int n = toIdx - fromIdx + 1;
        if (n >= 0) {
            System.arraycopy(src, fromIdx, dest, fromIdx, n);
            // Below is the exact functionality of above System.arraycopy()
            /*
            for (int i = 0; i < n; ++i) {
                dest[fromIdx + i] = src[fromIdx + i];
            }
             */
        }
    }

    public static void main(String[] args) {
        //int[] arr = {1,3,2,3,1};
        int [] arr = {11, 8, 15, 5, 4, 11, 2, 3};
        ReversePairs obj = new ReversePairs();
        int count = obj.reversePairs(arr);
        System.out.println("Count : " + count);
    }
}
