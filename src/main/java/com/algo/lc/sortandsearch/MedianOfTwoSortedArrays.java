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

    public double findMedianOfTwoSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 + n2 == 1) {
            return n1 == 1 ? nums1[0] : nums2[0];
        }
        boolean isOdd = false;

        if ((n1 + n2) % 2 == 1) {
            isOdd = true;
        }
        int mid = (n1 + n2)/2;
        int i = 0, j = 0, k = -1;
        int prev = -1, curr = -1;

        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                curr = nums1[i++];
            } else {
                curr = nums2[j++];
            }
            ++k;
            if (k == mid) {
                if (isOdd) {
                    return curr;
                } else {
                    return (double)(prev + curr)/2;
                }
            }
            prev = curr;
        }
        while (i < n1) {
            curr = nums1[i++];
            ++k;
            if (k == mid) {
                if (isOdd) {
                    return curr;
                } else {
                    return (double)(prev + curr)/2;
                }
            }
            prev = curr;
        }
        while (j < n2) {
            curr = nums2[j++];
            ++k;
            if (k == mid) {
                if (isOdd) {
                    return curr;
                } else {
                    return (double)(prev + curr)/2;
                }
            }
            prev = curr;
        }
        return -1;
    }

    public double findMedianOfTwoSortedArraysOptimized(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 + n2 == 1) {
            return n1 == 1 ? nums1[0] : nums2[0];
        }
        // Ensure n1 is smaller array for simplicity
        // We always want our search space to be small to have
        // better time complexity, hence n1 to be small
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int n = n1 + n2;
        int mid = (n1 + n2 + 1) / 2;
        int low = 0, high = n1;

        while (low <= high) {
            int mid1 = low + (high - low)/2; // Mid index of nums1
            int mid2 = mid - mid1; // mid index of nums2

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            // Determine values of l1, l2, r1 and r2
            if (mid1 < n1) {
                r1 = nums1[mid1];
            }
            if (mid2 < n2) {
                r2 = nums2[mid2];
            }
            if (mid1 - 1 >= 0) {
                l1 = nums1[mid1 - 1];
            }
            if (mid2 - 1 >= 0) {
                l2 = nums2[mid2 - 1];
            }

            if (l1 <= r2 && l2 <= r1) {
                // The partition is correct, we found the median
                if (n % 2 == 1) {
                    return Math.max(l1, l2);
                } else {
                    return ((double)(Math.max(l1, l2) + Math.min(r1, r2))) / 2.0;
                }
            } else if (l1 > r2) {
                // Move towards the left side of nums1
                high = mid1 - 1;
            } else {
                // l2 > r1 --> so move towards right side of nums1
                low = mid1 + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int [] nums1 = {1, 2};
        int [] nums2 = {3, 4};
        MedianOfTwoSortedArrays obj = new MedianOfTwoSortedArrays();
        double median = obj.findMedianOfTwoSortedArrays(nums1, nums2);
        System.out.println("Median : " + median);
        median = obj.findMedianOfTwoSortedArraysOptimized(nums1, nums2);
        System.out.println("Median : " + median);

        nums1 = new int[] {1, 5, 9, 15, 20};
        nums2 = new int[] {2, 6, 10, 12, 14, 16, 30};
        median = obj.findMedianOfTwoSortedArraysOptimized(nums1, nums2);
        System.out.println("Median : " + median);
    }
}
