package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Powerset
 *
 * Write a function that takes in an array of unique integers and returns
 * its powerset. The powerset P(X) of a set X is the set of all subsets
 * of X. For example, the powerset of [1,2] is [[], [1], [2], [1,2]].
 *
 * Note that the sets in the powerset do not need to be in any particular order
 *
 * Sample input: array = [1, 2, 3]
 *
 * Sample output:
 * [[], [1], [2], [3], [1,2], [1,3], [2,3],[1,2,3]]
 */
public class PowerSet {
    public static List<List<Integer>> powersetL(List<Integer> array) {
        List<List<Integer>> psets = new ArrayList<>();
        psets.add(new ArrayList<>());

        for (Integer num : array) {
            int len = psets.size();
            for (int i = 0; i < len; ++i) {
                List<Integer> newPset = new ArrayList<>(psets.get(i));
                newPset.add(num);
                psets.add(newPset);
            }
        }
        return psets;
    }

    public static List<List<Integer>> powersetR(List<Integer> array) {
        return powersetHelper(array, array.size() - 1);
    }

    public static List<List<Integer>>
    powersetHelper(List<Integer> array, int idx) {
        if (idx < 0) {
            List<List<Integer>> emptySet = new ArrayList<>();
            emptySet.add(new ArrayList<>());
            return emptySet;
        }
        int ele = array.get(idx);
        List<List<Integer>> prevSubsets = powersetHelper(array, idx - 1);
        int len = prevSubsets.size();
        for (int i = 0; i < len; ++i) {
            List<Integer> prevSubset = prevSubsets.get(i);
            List<Integer> currSubset = new ArrayList<>(prevSubset);
            currSubset.add(ele);
            prevSubsets.add(currSubset);
        }

        return prevSubsets;
    }

    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3);
        List<List<Integer>> psets = powersetL(array);
        System.out.println("Power sets : ");
        for (List<Integer> set : psets) {
            for (int num : set) {
                System.out.printf("%d, ", num);
            }
            System.out.println();
        }

        List<List<Integer>> psets2 = powersetR(array);
        System.out.println("Power sets 2: ");
        for (List<Integer> set : psets2) {
            System.out.printf("%s", "[");
            for (int num : set) {
                System.out.printf("%d, ", num);
            }
            System.out.println("]");
        }
    }
}
