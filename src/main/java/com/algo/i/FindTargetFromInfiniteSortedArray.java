package com.algo.i;

public class FindTargetFromInfiniteSortedArray {
    public static int
    binarySearchUnknownLength(int[] array, int k) {
        int p = 0;

        while (true) {
            int idx = (1 << p) - 1;
            try {
                if (array[idx] == k) {
                    return idx;
                } else if (array[idx] > k) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
            ++p;
        }

        // Binary search between indices 2 ^ (p - 1) and 2^p - 2
        int left = 1 << (p - 1), right = (1 << p) - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            try {
                if (array[mid] == k) {
                    return mid;
                } else if (array[mid] > k) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage() + " 2");
                right = mid - 1;
            }
        }
        return - 1;
    }

    public static void main(String[] args) {
        int [] a = {1, 3, 5, 6, 6, 8, 20, 22, 24, 30, 35, 38, 40, 45, 50, 55, 55, 60, 100, 102};
        int k = 100;
        int targetIndex = binarySearchUnknownLength(a, k);
        System.out.println("Target index : " + targetIndex);
    }
}
