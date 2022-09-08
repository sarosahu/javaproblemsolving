package com.algo.i;

public class FetchBoundaryIndicesFromKSubArrays {
    public static int[]
    fetchBoundaryIndices(int [] a, int k) {
        int start = 0;
        int end = start + k - 1;
        if (k > a.length) {
            return new int[] {-1, -1};
        }
        for (int i = 1; i < a.length && start + k - 1 < a.length; ++i) {
            int curr = a[i];
            if (curr >= a[start] || curr >= a[end]) {
                start += 1;
                end = start + k - 1;
            } else {
                if (i == end - 1) {
                    return new int[] {start, end};
                }
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 4, 2, 3, 1, 3, 1, 5, 4, 2, 6};
        //int[] array = {2, 3, 4, 5, 6, 7, 8};
        int[] array = {2, 3, 4, 5, 6, 7, 6, 5, 9};
        int k = 4;
        int[] indices = fetchBoundaryIndices(a, k);
        int[] indices2 = fetchBoundaryIndices(array, k);
    }
}
