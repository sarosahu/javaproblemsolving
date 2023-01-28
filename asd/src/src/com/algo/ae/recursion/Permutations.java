package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Permutations
 *
 * Write a function that takes in an array of unique integers
 * and returns an array of all permutations of those integers
 * in no particular order.
 *
 * If the input array is empty, the function should return an
 * empty array.
 *
 * Sample Input: [1, 2, 3]
 *
 * Sample Output
 * [
 *  [1, 2, 3],
 *  [1, 3, 2],
 *  [2, 1, 3],
 *  [2, 3, 1],
 *  [3, 1, 2],
 *  [3, 2, 1]
 * ]
 */
public class Permutations {

    /**
     *
     * @param array
     * @return List of all the permutations
     * Time: O(N*N!), Space : O(N*N!)
     */
    public static List<List<Integer>> getPermutations(List<Integer> array) {
        // Write your code here.
        List<List<Integer>> results = new ArrayList<>();
        permutationsHelper(array, 0, results);
        return results;
    }

    private static void permutationsHelper(List<Integer> array,
                                           int idx,
                                           List<List<Integer>> results) {
        if (idx == array.size() - 1) {
            results.add(new ArrayList<>(array));
            return;
        }
        for (int i = idx; i < array.size(); ++i) {
            swap(array, idx, i);
            permutationsHelper(array, idx + 1, results);
            swap(array, idx, i);
        }
    }

    private static void swap(List<Integer> array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    /**
     *
     * @param array
     * @return List of all permutations
     * Time: O(N*N!), Space : O(N*N!)
     */
    public static List<List<Integer>> getPermutations2(List<Integer> array) {
        // Write your code here.
        List<List<Integer>> results = new ArrayList<>();
        if (array.isEmpty()) {
            return results;
        }
        Collections.sort(array);
        do {
            List<Integer> newArray = new ArrayList<>(array);
            results.add(newArray);
        } while (nextPermutation(array));
        return results;
    }

    public static boolean nextPermutation(List<Integer> array) {
        int i = array.size() - 2;
        while (i >= 0 && array.get(i) > array.get(i + 1)) {
            --i;
        }
        if (i == -1) {
            return false;
        }

        // Here we need to find out the smallest number from index i + 1 to array.size() - 1
        // which must be greater than array[i]
        int k = array.size() - 1;
        while (k > i) {
            if (array.get(k) > array.get(i)) {
                break;
            }
            --k;
        }
        swap(array, i, k);
        i++;
        int j = array.size() - 1;
        while (i < j) {
            swap(array, i, j);
            ++i;
            --j;
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        /*List<List<Integer>> permutations = getPermutations(Arrays.asList(1, 2, 3, 4));
        for (List<Integer> perm : permutations) {
            for (int num : perm) {
                System.out.printf("%d, ", num);
            }
            System.out.println();
        }*/

        List<List<Integer>> permutations2 = getPermutations(Arrays.asList(1, 2, 3, 4));
        System.out.println("Size : " + permutations2.size());
        for (List<Integer> perm : permutations2) {
            for (int num : perm) {
                System.out.printf("%d, ", num);
            }
            System.out.println();
        }

        List<Integer> array = Arrays.asList(1, 3, 4, 2);
        boolean nextPerm = nextPermutation(array);
        for (int num : array) {
            System.out.printf("%d, ", num);
        }
    }
}
