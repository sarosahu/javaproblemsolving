package com.algo.i;

public class FetchBoundaryIndicesFromKSubArrays {
    public static int[]
    fetchBoundaryIndices(int [] a, int k) {
        if (k > a.length) {
            return new int[] {-1, -1};
        }
        int start = 0;
        int end = start + k - 1;

        for (int i = 1; end < a.length && i < end;) {
            int curr = a[i];
            if (curr >= a[start] || curr >= a[end]) {
                start += 1;
                end = start + k - 1;
                i = start + 1;
            } else {
                if (i == end - 1) {
                    return new int[] {start, end};
                }
                ++i;
            }
        }
        return new int[] {-1, -1};
    }

    public static void main(String[] args) {
        int[][] arrayList = {

                {1, 2, 4, 2, 3, 1, 3, 1, 5, 4, 2, 6},
                {2, 3, 4, 5, 6, 7, 6, 5, 9},
                {3, 4, 2, 1, 2, 10, 9, 7, 6, 8, 5, 9, 10},
                {3, 4, 5, 1, 2, 10, 9, 7, 6, 8, 9, 9, 10},
        };
        int k = 5;
        for (int[] array : arrayList) {
            System.out.println("Array : ");
            for (int num : array) {
                System.out.printf("%d, ", num);
            }
            System.out.println();
            int[] indices = fetchBoundaryIndices(array, k);
            System.out.println("Indices : " + indices[0] + ", " + indices[1]);
            System.out.println();
        }
    }
}
