package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSets {
    public static List<List<Integer>>
    getPowerSetsL(List<Integer> array) {
        List<List<Integer>> subsets = new ArrayList<>();
        // We always have an empty set
        subsets.add(new ArrayList<>());
        for (int num : array) {
            int len = subsets.size();
            for (int i = 0; i < len; ++i) {
                List<Integer> existingSubset = subsets.get(i);
                List<Integer> newSubset = new ArrayList<>(existingSubset);
                newSubset.add(num);
                subsets.add(newSubset);
            }
        }
        return subsets;
    }

    public static List<List<Integer>>
    getPowerSetsR(List<Integer> array) {
        return powerSetsHelper(array, array.size() - 1);
    }

    public static List<List<Integer>>
    powerSetsHelper(List<Integer> array, int idx) {
        if (idx < 0) {
            List<List<Integer>> emptySet = new ArrayList<>();
            emptySet.add(new ArrayList<>());
            return emptySet;
        }
        int element = array.get(idx);
        List<List<Integer>> subsets = powerSetsHelper(array, idx - 1);
        int len = subsets.size();
        for (int i = 0; i < len; ++i) {
            List<Integer> existingSubset = subsets.get(i);
            List<Integer> newSubset = new ArrayList<>(existingSubset);
            newSubset.add(element);
            subsets.add(newSubset);
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3);
        List<List<Integer>> powerSets = getPowerSetsL(array);
        for (List<Integer> powerSet : powerSets) {
            System.out.printf("%s : ", "subset");
            for (int num: powerSet) {
                System.out.printf("%d ", num);
            }
            System.out.println();
        }

        List<List<Integer>> powerSets2 = getPowerSetsR(array);
        for (List<Integer> powerSet : powerSets2) {
            System.out.printf("%s : ", "subset");
            for (int num: powerSet) {
                System.out.printf("%d ", num);
            }
            System.out.println();
        }
    }
}
