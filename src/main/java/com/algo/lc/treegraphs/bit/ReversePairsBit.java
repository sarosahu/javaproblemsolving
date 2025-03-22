package com.algo.lc.treegraphs.bit;

import java.util.Arrays;

public class ReversePairsBit {
    private int search(int[] bit, int i) {
        int sum = 0;

        while (i < bit.length) {
            sum += bit[i];
            System.out.printf("[SEARCH]i => %d,", i);
            i += i & -i;
            System.out.printf("i => %d, ", i);
        }
        System.out.printf("SUM = [%d]\n", sum);
        System.out.println(">>>>>>>>>>>>>>>>>>");
        return sum;
    }

    private void insert(int[] bit, int i) {
        while (i > 0) {
            bit[i] += 1;
            System.out.printf("[INSERT]i => %d,", i);
            i -= i & -i;
            System.out.printf("i => %d\n\n", i);
        }
    }
    public int reversePairs(int[] nums) {
        int res = 0;
        int[] copy = Arrays.copyOf(nums, nums.length);
        int[] bit = new int[copy.length + 1];

        Arrays.sort(copy);
        System.out.println("Copy array : " + Arrays.toString(copy));
        for (int ele : nums) {
            System.out.println("Current element : " + ele);
            res += search(bit, index(copy, 2 * ele + 1));
            insert(bit, index(copy, ele));
        }

        return res;
    }

    private int index(int[] arr, int val) {
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int m = l + (r - l)/2;

            if (arr[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        System.out.println("index() returns : " + (l + 1));
        return l + 1;
    }

    public static void main(String[] args) {
        int[] arr = {11, 8, 15, 5, 4, 11, 2, 3};
        ReversePairsBit obj = new ReversePairsBit();
        int count = obj.reversePairs(arr);
        System.out.println("Number of reverse pairs count : " + count);
    }
}
