package com.algo.epi.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PowerSetWithDups {
    public static List<List<Integer>>
    generatePowerSet(List<Integer> input) {
        Collections.sort(input);
        List<List<Integer>> powerSet = new ArrayList<>();
        List<Integer> selectedSoFar = new ArrayList<>();
        generatePowerSetHelper(input, 0, selectedSoFar, powerSet);
        return powerSet;
    }

    private static void
    generatePowerSetHelper(List<Integer> input,
                           int fromIdx,
                           List<Integer> selectedSoFar,
                           List<List<Integer>> powerSet) {

        powerSet.add(new ArrayList<>(selectedSoFar));
        for (int i = fromIdx; i < input.size(); ++i) {
            if (i != fromIdx && input.get(i) == input.get(i - 1)) {
                continue;
            }
            selectedSoFar.add(input.get(i));
            generatePowerSetHelper(input, i + 1, selectedSoFar, powerSet);
            selectedSoFar.remove(selectedSoFar.size() - 1);
        }
    }

    private static void
    generatePowerSetHelper2(List<Integer> input,
                           int fromIdx,
                           List<Integer> selectedSoFar,
                           List<List<Integer>> powerSet) {
        if (fromIdx == input.size()) {
            powerSet.add(new ArrayList<>(selectedSoFar));
            return;
        }
        // Generate all subsets that contain input[toBeSelected]
        selectedSoFar.add(input.get(fromIdx));
        generatePowerSetHelper2(input, fromIdx + 1, selectedSoFar, powerSet);

        // Generate all subsets that don't contain input[toBeSelected]
        selectedSoFar.remove(selectedSoFar.size() - 1);
        generatePowerSetHelper2(input, fromIdx + 1, selectedSoFar, powerSet);
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 2);
        List<List<Integer>> powerSets3 = generatePowerSet(array);
        System.out.println("Subsets : ");
        for (List<Integer> powerSet : powerSets3) {
            System.out.println(powerSet.toString());
        }
    }
}
