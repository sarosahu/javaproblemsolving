package com.algo.lc.sortandsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
public class MedianOfTwoSortedArrays {
    static class Median {
        int size;
        int num1;
        int num2;
        public Median(int size, int num1, int num2) {
            this.size = size;
            this.num1 = num1;
            this.num2 = num2;
        }
        public double getMedian() {
            return (double)(num1 + num2) / 2;
        }
    }

    // Time: O(m + n), space O(m + n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        List<Integer> nums = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            int first = nums1[i];
            int second = nums2[j];
            if (first < second) {
                nums.add(first);
                ++i;
            } else if (second < first) {
                nums.add(second);
                ++j;
            } else {
                nums.add(first);
                nums.add(second);
                ++i;
                ++j;
            }
        }
        while (i < nums1.length) {
            nums.add(nums1[i]);
            ++i;
        }
        while (j < nums2.length) {
            nums.add(nums2[j]);
            ++j;
        }
        int n = nums.size();
        if (n % 2 == 1) {
            return (double)nums.get(n/2);
        } else {
            int m = n/2;
            int mid1 = nums.get(m);
            int mid2 = nums.get(m - 1);
            return (double)(mid1 + mid2)/2;
        }
    }

    // Time : O(m + n), Space : O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int N = nums1.length + nums2.length;
        Median median = new Median(N, 0, 0);
        int i = 0, j = 0, k = -1;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                k += 1;
                if (hasMedian(k, median, nums1, i++)) {
                    return median.getMedian();
                }
            } else if (nums2[j] < nums1[i]) {
                k += 1;
                if (hasMedian(k, median, nums2, j++)) {
                    return median.getMedian();
                }
            } else {
                k += 1;
                if (hasMedian(k, median, nums1, i++)) {
                    return median.getMedian();
                }

                k += 1;
                if (hasMedian(k, median, nums2, j++)) {
                    return median.getMedian();
                }
            }

        }

        while (i < nums1.length) {
            k += 1;
            if (hasMedian(k, median, nums1, i++)) {
                return median.getMedian();
            }
        }

        while (j < nums2.length) {
            k += 1;
            if (hasMedian(k, median, nums2, j++)) {
                return median.getMedian();
            }
        }

        return (double)0.0;
    }

    public boolean hasMedian(int k, Median median, int []nums, int idx) {
        boolean isEven = median.size % 2 == 0;
        if (isEven) {
            if (k == (median.size/2) - 1) {
                median.num1 = nums[idx];
            } else if (k == median.size/2) {
                median.num2 = nums[idx];
                return true;
            }
        } else {
            if (k == median.size/2) {
                median.num1 = nums[idx];
                median.num2 = nums[idx];
                return true;
            }
        }
        return false;
    }
}
