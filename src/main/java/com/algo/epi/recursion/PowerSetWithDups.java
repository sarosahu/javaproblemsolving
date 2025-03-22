package com.algo.epi.recursion;

import java.util.*;

public class PowerSetWithDups {

    // { Iterative approach.
    // Time: O(N*2^N), Space: O(LogN)
    public List<List<Integer>>
    subsetWithDuplicateI(int [] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        int subsetSize = 0;
        for (int i = 0; i < nums.length; ++i) {
            int startIdx = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0;
            subsetSize = subsets.size();

            for (int j = startIdx; j < subsetSize; ++j) {
                List<Integer> prevSubset = subsets.get(j);
                List<Integer> newSubset = new ArrayList<>(prevSubset);
                newSubset.add(nums[i]);
                subsets.add(newSubset);
            }
        }
        return subsets;
    }
    // } Iterative solution.

    // { Recursive approach : Time: O(N* 2^N), space : O(N)
    public List<List<Integer>>
    generatePowerSet(List<Integer> input) {
        Collections.sort(input);
        List<List<Integer>> powerSet = new ArrayList<>();
        List<Integer> selectedSoFar = new ArrayList<>();
        generatePowerSetHelper(input, 0, selectedSoFar, powerSet);
        return powerSet;
    }

    private void
    generatePowerSetHelper(List<Integer> input,
                           int fromIdx,
                           List<Integer> selectedSoFar,
                           List<List<Integer>> powerSet) {

        powerSet.add(new ArrayList<>(selectedSoFar));
        for (int i = fromIdx; i < input.size(); ++i) {
            if (i != fromIdx && Objects.equals(input.get(i), input.get(i - 1))) {
                continue;
            }
            selectedSoFar.add(input.get(i));
            generatePowerSetHelper(input, i + 1, selectedSoFar, powerSet);
            selectedSoFar.remove(selectedSoFar.size() - 1);
        }
    }
    // } Recursive approach

    public static void main(String[] args) {
        PowerSetWithDups obj = new PowerSetWithDups();
        List<Integer> array = Arrays.asList(1, 2, 3, 2);
        List<List<Integer>> powerSets3 = obj.generatePowerSet(array);
        System.out.println("Subsets : ");
        for (List<Integer> powerSet : powerSets3) {
            System.out.println(powerSet.toString());
        }

        int[] nums = {1, 2, 3, 2};
        List<List<Integer>> powerSets = obj.subsetWithDuplicateI(nums);
        System.out.println("Subsets 2 : ");
        for (List<Integer> powerSet : powerSets) {
            System.out.println(powerSet.toString());
        }
    }
}
