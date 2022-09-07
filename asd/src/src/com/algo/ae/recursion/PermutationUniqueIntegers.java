package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationUniqueIntegers {
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

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<List<Integer>> permutations = getPermutations(array);
        for (List<Integer> permutation : permutations) {
            for (int num: permutation) {
                System.out.printf("%d ", num);
            }
            System.out.println();
        }
    }
}
