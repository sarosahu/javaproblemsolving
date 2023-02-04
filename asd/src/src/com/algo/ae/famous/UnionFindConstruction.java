package com.algo.ae.famous;

import java.util.HashMap;
import java.util.Map;

/**
 * Union Find
 *
 * Write a UnionFind class that implements the union-find (also called a
 * disjoint set) data structure. This class should support 3 methods:
 *
 * The union-find data structure is similar to a traditional set data
 * structure, in that it contains a collection of unique values. However,
 * these values are spread out amongst a variety of distinct disjoint
 * sets, meaning that no set can have duplicate values and no two sets
 * can contain the same value.
 *
 * - createSet(value) : Adds a given value in a new set containing on that value
 * - union(valueOne, valueTwo): Takes in 2 values and determines which sets
 *   they are in. If they are in different sets, the sets are combined into
 *   a single set. If either value is not in a set or they are in the same set,
 *   the function should have no effect.
 * - find(value): Returns the "representative" value of the set for which
 *   a value belongs to. This can be any value in the set, but it should
 *   always be the same value, regardless of which value in the set find
 *   is passed. If the value is not in a set, the function should return
 *   null/None. Note that after a set is part of a union, its representative
 *   can potentially change.
 *
 *  You can assume createSet() will never be called with same value twice.
 *
 *  Sample Usage:
 *  createSet(5): null
 *  createSet(10): null
 *  find(5): 5
 *  find(10): 10
 *  union(5, 10): null
 *  find(5): 5
 *  find(10): 5
 *  createSet(20): null
 *  find(20): 20
 *  union(20, 10): null
 *  find(5): 5
 *  find(10): 5
 *  find(20): 5
 *
 */
public class UnionFindConstruction {
    static class UnionFind {
        // parents is a map from node to its parent.
        private HashMap<Integer, Integer> parents = new HashMap<Integer, Integer>();
        // Time: O(1), Space: O(1)
        public void createSet(int value) {
            parents.put(value, value);
        }

        // Time: O(N), Space O(1)
        public Integer find(int value) {
            if (!parents.containsKey(value)) {
                return null;
            }
            int currParent = value;
            while (currParent != parents.get(currParent)) {
                currParent = parents.get(currParent);
            }
            return currParent;
        }

        // Time: O(N), Space O(1) -- N is the total number of values
        public void union(int valueOne, int valueTwo) {
            if (!parents.containsKey(valueOne) || !parents.containsKey(valueTwo)) {
                return;
            }
            int valueOneRoot = find(valueOne);
            int valueTwoRoot = find(valueTwo);
            parents.put(valueTwoRoot, valueOneRoot);
        }
    }

    static class UnionFind2 {
        private Map<Integer, Integer> parents = new HashMap<>();
        private Map<Integer, Integer> ranks = new HashMap<>();

        // Time: O(1), Space: O(1)
        public void createSet(int value) {
            parents.put(value, value);
            ranks.put(value, 0);
        }

        // Time: O(log(N)), Space: O(1)
        public Integer find(int value) {
            if (!parents.containsKey(value)) {
                return null;
            }
            int currParent = value;
            while (currParent != parents.get(currParent)) {
                currParent = parents.get(currParent);
            }
            return currParent;
        }

        // Time: O(log(N)), Space: O(1)
        public void union(int valueOne, int valueTwo) {
            if (!parents.containsKey(valueOne) || !parents.containsKey(valueTwo)) {
                return;
            }

            int valueOneRoot = find(valueOne);
            int valueTwoRoot = find(valueTwo);
            if (ranks.get(valueOneRoot) < ranks.get(valueTwoRoot)) {
                parents.put(valueOneRoot, valueTwoRoot);
            } else if (ranks.get(valueOneRoot) > ranks.get(valueTwoRoot)) {
                parents.put(valueTwoRoot, valueOneRoot);
            } else {
                parents.put(valueTwoRoot, valueOneRoot);
                ranks.put(valueOneRoot, ranks.get(valueOneRoot) + 1);
            }
        }
    }

    static class UnionFindE {
        private Map<Integer, Integer> parents = new HashMap<>();
        private Map<Integer, Integer> ranks = new HashMap<>();
        // Time; O(1), Space: O(1)
        public void createSet(int value) {
            parents.put(value, value);
            ranks.put(value, 0);
        }

        // Time: O(alpha(n)) approximately O(1)
        // Space: O(alpha(n)) approximately O(1)
        // Where n is the total number of values
        public Integer find(int value) {
            if (!parents.containsKey(value)) {
                return null;
            }

            if (value != parents.get(value)) {
                parents.put(value, find(parents.get(value)));
            }
            return parents.get(value);
        }

        // Time: O(alpha(n)) approximately O(1)
        // Space: O(alpha(n)) approximately O(1)
        // Where n is the total number of values
        public void union(int valueOne, int valueTwo) {
            if (!parents.containsKey(valueOne) || !parents.containsKey(valueTwo)) {
                return;
            }

            int valueOneRoot = find(valueOne);
            int valueTwoRoot = find(valueTwo);
            if (ranks.get(valueOneRoot) < ranks.get(valueTwoRoot)) {
                parents.put(valueOneRoot, valueTwoRoot);
            } else if (ranks.get(valueOneRoot) > ranks.get(valueTwoRoot)) {
                parents.put(valueTwoRoot, valueOneRoot);
            } else {
                parents.put(valueTwoRoot, valueOneRoot);
                ranks.put(valueOneRoot, ranks.get(valueOneRoot) + 1);
            }
        }
    }
}
