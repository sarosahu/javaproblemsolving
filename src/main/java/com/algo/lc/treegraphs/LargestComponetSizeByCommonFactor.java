package com.algo.lc.treegraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * You are given an integer array of unique positive integers nums. Consider the following graph:
 *
 * There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
 * There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 *
 * Example 1:
 * Input nums = [4, 6, 15, 35]
 * Output: 4
 *
 * Example 2:
 * Input: nums = [20,50,9,63]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [2,3,6,7,4,12,21,39]
 * Output: 8
 */
public class LargestComponetSizeByCommonFactor {

    public int largestComponentSize(int[] A) {

        int maxValue = Arrays.stream(A).reduce(A[0], (x, y) -> x > y ? x : y);
        DisjointSetUnion dsu = new DisjointSetUnion(maxValue);
        // attribute each element to all the groups that lead by its factors.
        for (int num : A) {
            for (int factor=2; factor < (int)(Math.sqrt(num))+1; ++ factor)
                if (num % factor == 0) {
                    dsu.union(num, factor);
                    dsu.union(num, num / factor);
                }
        }

        // count the size of group one by one
        int maxGroupSize = 0;
        HashMap<Integer, Integer> groupCount = new HashMap<>();
        for (int num : A) {
            Integer groupId = dsu.find(num);
            Integer count = groupCount.getOrDefault(groupId, 0);
            groupCount.put(groupId, count+1);
            maxGroupSize = Math.max(maxGroupSize, count+1);
        }

        return maxGroupSize;
    }

    public int largestComponentSize2(int[] nums) {
        int maxValue = Arrays.stream(nums).reduce(nums[0], (x, y) -> x > y ? x : y);
        DisjointSetUnion dsu = new DisjointSetUnion(maxValue);

        Map<Integer, Integer> numFactorMap = new HashMap<>();

        // Union - Find on the prime factors.
        for (int num : nums) {
            List<Integer> primeFactors = new ArrayList<>(new HashSet<>(
                    this.primeDecompose(num)
            ));

            // Map a number to its first prime factor.
            numFactorMap.put(num, primeFactors.get(0));

            // Merge all the groups that contain the prime factors.
            for (int i = 0; i < primeFactors.size() - 1; ++i) {
                dsu.union(primeFactors.get(i), primeFactors.get(i + 1));
            }
        }

        // Count the size of the group one by one.
        int maxGroupSize = 0;
        Map<Integer, Integer> groupCount = new HashMap<>();
        for(int num : nums) {
            Integer groupId = dsu.find(numFactorMap.get(num));
            Integer count = groupCount.getOrDefault(groupId, 0);
            groupCount.put(groupId, count + 1);
            maxGroupSize = Math.max(maxGroupSize, count + 1);
        }

        return maxGroupSize;
    }

    protected List<Integer> primeDecompose(int num) {
        List<Integer> primeFactors = new ArrayList<>();
        int factor = 2;
        while (num >= factor * factor) {
            if (num % factor == 0) {
                primeFactors.add(factor);
                num /= factor;
            } else {
                factor += 1;
            }
        }
        primeFactors.add(num);

        return primeFactors;
    }

    static class DisjointSetUnion {
        private int[] parent;
        private int[] size;

        public DisjointSetUnion(int size) {
            this.parent = new int[size + 1];
            this.size = new int[size + 1];
            for (int i = 0; i <= size; ++i) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }

        // Return the component id that the element x belongs to
        public int find(int x) {
            if (this.parent[x] != x) {
                this.parent[x] = this.find(this.parent[x]);
            }
            return this.parent[x];
        }

        /**
         * Merge the two components that x, y belongs to respectively
         * and return the merged component id as the result.
         */
        public int union(int x, int y) {
            int px = this.find(x);
            int py = this.find(y);

            // If two nodes share the same group
            if (px == py) {
                return px;
            }

            // Otherwise, connect the two sets (components)
            if (this.size[px] > this.size[py]) {
                // Add the node to the union with less numbers,
                // keeping px as the index of the smaller component
                int tmp = px;
                px = py;
                py = tmp;
            }

            // Add the smaller component to the larger one.
            this.parent[px] = py;
            this.size[py] += this.size[px];

            return py;
        }
    }

    public static void main(String[] args) {
        int [] nums = {2,3,6,7,4,12,21,39};
        LargestComponetSizeByCommonFactor obj = new LargestComponetSizeByCommonFactor();
        int largestComponentSize = obj.largestComponentSize2(nums);
        System.out.println("Max component size : " + largestComponentSize);
    }
}
