package com.algo.ae.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * Validate Subsequence
 *
 * Given 2 non-empty arrays of integers, write a function that determines if the
 * second array is a subsequence of the first one.
 *
 * A subsequence of an array is a set of numbers that aren't necessarily adjacent
 * in the array but that are in the same order as they appear in the array. For
 * instance, the numbers [1, 3, 4] form a subsequence of the array [1, 2, 3, 4],
 * and so do the numbers [2, 4]. Note that a single number in an array and the
 * array itself are both valid subsequences of the array.
 */
public class ValidSubsequence {
    // Time: O(N), space: O(1)
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        if (sequence.size() > array.size()) {
            return false;
        }
        int arrIdx = 0;
        int seqIdx = 0;
        while (arrIdx < array.size() && seqIdx < sequence.size()) {
            if (array.get(arrIdx).equals(sequence.get(seqIdx))) {
                ++seqIdx;
            }
            ++arrIdx;
        }
        return seqIdx == sequence.size();
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);
        List<Integer> sequence = Arrays.asList(1, 6, -1, 10);

        if (isValidSubsequence(array, sequence)) {
            System.out.println("Sequence array is a valid subsequence inside array");
        } else {
            System.out.println("Sequence array is not a valid subsequence inside array");
        }
    }
}
