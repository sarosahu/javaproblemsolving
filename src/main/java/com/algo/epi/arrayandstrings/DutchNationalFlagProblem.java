package com.algo.epi.arrayandstrings;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.swap;

/**
 * EPI. Array 6.1
 * Given an array A and an index i, rearrange the array in such a way that
 * all the elements less than A[i] appears first, followed by elements equal
 * to A[i], followed by elements > A[i].
 */
public class DutchNationalFlagProblem {
    void dutchFlagPartition(List<Integer> array, int idx) {
        if (idx < 0 || idx >= array.size()) {
            return;
        }
        int pivot = array.get(idx);

        int smaller = 0, larger = array.size() - 1;
        int equal = 0;

        while (equal <= larger) {
            int curr = array.get(equal);
            if (curr < pivot) {
                swap(array, equal, smaller);
                ++equal;
                ++smaller;
            } else if (curr > pivot) {
                swap(array, equal, larger);
                --larger;
            } else {
                ++equal;
            }
        }
    }

    public static void main(String[] args) {
        DutchNationalFlagProblem obj = new DutchNationalFlagProblem();
        List<Integer> array = Arrays.asList(10, 9, 1, 7, 6, 5, 7, 2, 3, 7, 9, 4);
        System.out.println("Array prior partition : ");
        System.out.println(array);
        obj.dutchFlagPartition(array, 3);
        System.out.println("Array after partition : ");
        System.out.println(array);
    }
}
