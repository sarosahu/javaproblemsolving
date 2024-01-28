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

    public static List<List<Integer>>
    generatePowerSet(List<Integer> input) {
        List<List<Integer>> powerSet = new ArrayList<>();
        List<Integer> selectedSoFar = new ArrayList<>();
        generatePowerSetHelper(input, 0, selectedSoFar, powerSet);
        return powerSet;
    }

    private static void
    generatePowerSetHelper(List<Integer> input,
                           int toBeSelected,
                           List<Integer> selectedSoFar,
                           List<List<Integer>> powerSet) {
        if (toBeSelected == input.size()) {
            powerSet.add(new ArrayList<>(selectedSoFar));
            return;
        }
        // Generate all subsets that contain input[toBeSelected]
        selectedSoFar.add(input.get(toBeSelected));
        generatePowerSetHelper(input, toBeSelected + 1, selectedSoFar, powerSet);
        // Generate all subsets that don't contain input[toBeSelected]
        selectedSoFar.remove(selectedSoFar.size() - 1);
        generatePowerSetHelper(input, toBeSelected + 1, selectedSoFar, powerSet);
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

        List<List<Integer>> powerSets3 = generatePowerSet(array);
        System.out.println("Subsets : ");
        for (List<Integer> powerSet : powerSets3) {
            System.out.println(powerSet.toString());
        }
    }
}
