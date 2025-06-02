package com.algo.ae.recursion;

import java.util.*;

public class PermutationsWithDuplicates {
    public static List<List<Integer>>
    getPermutationsWithDuplicates(List<Integer> array) {
        Map<Integer, Integer> countsTable = buildFrequencyTable(array);
        List<List<Integer>> result = new ArrayList<>();

        getPermutationsHelper(countsTable, new ArrayList<>(), array.size(), result);
        return result;
    }

    public static void getPermutationsHelper(Map<Integer, Integer> countsTable,
                                             List<Integer> permutation,
                                             int remaining,
                                             List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(permutation));
            return;
        }
        // Remaining permutations
        for (int num : countsTable.keySet()) {
            int count = countsTable.get(num);
            if (count > 0) {
                countsTable.put(num, count - 1);
                permutation.add(num);
                getPermutationsHelper(countsTable, permutation, remaining - 1, result);
                permutation.remove(permutation.size() - 1);
                countsTable.put(num, count);
            }
        }
    }
    public static Map<Integer, Integer>
    buildFrequencyTable(List<Integer> array) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : array) {
            int count = counts.getOrDefault(num, 0);
            counts.put(num, count + 1);
        }
        return counts;
    }

    public static List<List<Integer>>
    permutationsWithDuplicates(List<Integer> array) {
        array.sort(Integer::compare);
        List<List<Integer>> perms = new ArrayList<>();
        permutationsWithDuplicatesHelper(0, array, perms);
        return perms;
    }

    private static void
    permutationsWithDuplicatesHelper(int currIndex,
                                     List<Integer> array,
                                     List<List<Integer>> perms) {
        if (currIndex == array.size()) {
            perms.add(new ArrayList<>(array));
            return;
        }
        for (int i = currIndex; i < array.size(); ++i) {
            if (i != currIndex && array.get(i).equals(array.get(i - 1))) {
                continue;
            }
            swap(array, i, currIndex);
            permutationsWithDuplicatesHelper(currIndex + 1, array, perms);
            swap(array, i, currIndex);
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
        //List<Integer> array = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        //List<Integer> array = Arrays.asList(1, 2, 2);
        List<Integer> array = Arrays.asList(2, 1, 2);
        System.out.println("Calling getPermutationsWithDuplicates()");
        List<List<Integer>> permutations = getPermutationsWithDuplicates(array);
        for (List<Integer> permutation : permutations) {
            for (int num: permutation) {
                System.out.printf("%d ", num);
            }
            System.out.println();
        }
        System.out.println("Calling permutationsWithDuplicates()");
        List<List<Integer>> permutations2 = permutationsWithDuplicates(array);
        for (List<Integer> permutation : permutations2) {
            for (int num: permutation) {
                System.out.printf("%d ", num);
            }
            System.out.println();
        }
    }
}
