package com.algo.lc.arraysandstrings;

import java.util.*;

/**
 * Approach 3: "No-Sort"
 * What if you cannot modify the input array, and you want to avoid copying it due to memory constraints?
 *
 * We can adapt the hashset approach above to work for an unsorted array. We can put a combination of
 * three values into a hashset to avoid duplicates. Values in a combination should be ordered (e.g. ascending).
 * Otherwise, we can have results with the same values in the different positions.
 *
 * Algorithm
 *
 * The algorithm is similar to the hashset approach above. We just need to add few optimizations so that it
 * works efficiently for repeated values:
 *
 * Use another hashset dups to skip duplicates in the outer loop.
 * Without this optimization, the submission will time out for the test case with 3,000 zeroes. This case is
 * handled naturally when the array is sorted.
 * Instead of re-populating a hashset every time in the inner loop, we can use a hashmap and populate it once.
 * Values in the hashmap will indicate whether we have encountered that element in the current iteration. When
 * we process nums[j] in the inner loop, we set its hashmap value to i. This indicates that we can now use nums[j]
 * as a complement for nums[i].
 * This is more like a trick to compensate for container overheads. The effect varies by language, e.g. for C++
 * it cuts the runtime in half. Without this trick the submission may time out.
 */
public class ThreeSumToZeroNoSort {
    public static List<List<Integer>>
    threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(res);
    }

    public static void main(String[] args) {
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        System.out.println("Result of threeSum, here are the triplets:");
        for (List<Integer> triplet : result) {
            System.out.printf("%d, %d, %d\n", triplet.get(0), triplet.get(1), triplet.get(2));
        }
    }
}
