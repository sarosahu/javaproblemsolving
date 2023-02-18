package com.algo.ae.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Youngest Common Ancestor
 *
 * You're given three inputs, all of which are instances of an AncestralTree class
 * that have an ancestor property pointing to their youngest ancestor. The first
 * input is the top ancestor in an ancestral tree (i.e., the only instance that
 * has no ancestor -- its ancestor property points to None/null, and the other two
 * inputs are descendants in the ancestral tree.
 *
 * Write a function that returns the youngest common ancestor to the two descendants.
 *
 * Note that a descendant is considered its owner ancestor. So in the simple ancestral
 * tree below, the youngest common ancestor to nodes A and B is node A.
 *
 * // { The youngest common ancestor to nodes A and B is node A.
 *     A
 *   /
 * B
 * // }
 *
 * Sample Input:
 * {
 *   "topAncestor": "A",
 *   "descendantOne": "E",
 *   "descendantTwo": "I",
 *   "ancestralTree": {
 *     "nodes": [
 *       {"ancestor": null, "id": "A", "name": "A"},
 *       {"ancestor": "A", "id": "B", "name": "B"},
 *       {"ancestor": "A", "id": "C", "name": "C"},
 *       {"ancestor": "B", "id": "D", "name": "D"},
 *       {"ancestor": "B", "id": "E", "name": "E"},
 *       {"ancestor": "C", "id": "F", "name": "F"},
 *       {"ancestor": "C", "id": "G", "name": "G"},
 *       {"ancestor": "D", "id": "H", "name": "H"},
 *       {"ancestor": "D", "id": "I", "name": "I"}
 *     ]
 *   }
 * }
 *
 *                         A
 *                       /  \
 *                     B     C
 *                   /  \   /  \
 *                 D     E F    G
 *               /  \
 *             H     I
 *
 *  Sample Output: node B
 */
public class YoungestCommonAncestor {
     // Time: O(d), space : O(1) -- d is depth(height) of the ancestral tree
    static class AncestralTree {
        public char name;
        public AncestralTree ancestor;

        AncestralTree(char name) {
            this.name = name;
            this.ancestor = null;
        }

        // This method is for testing only.
        void addAsAncestor(AncestralTree[] descendants) {
            for (AncestralTree descendant : descendants) {
                descendant.ancestor = this;
            }
        }
    }

    public AncestralTree getYoungestCommonAncestor(AncestralTree topAncestor,
                                                   AncestralTree descendantOne,
                                                   AncestralTree descendantTwo) {
        int depth1 = getDepth(descendantOne);
        int depth2 = getDepth(descendantTwo);
        AncestralTree lower = depth1 > depth2 ? descendantOne : descendantTwo;
        int diff = Math.abs(depth1 - depth2);
        AncestralTree upper = lower == descendantOne ? descendantTwo : descendantOne;
        while (diff > 0) {
            lower = lower.ancestor;
            --diff;
        }
        if (upper == lower) {
            return upper;
        }
        while (upper.ancestor != lower.ancestor && upper != lower) {
            upper = upper.ancestor;
            lower = lower.ancestor;
        }
        return upper.ancestor;
    }

    private int getDepth(AncestralTree tree) {
        int level=0;
        while (tree.ancestor != null) {
            tree = tree.ancestor;
            ++level;
        }
        return level;
    }

    // { Approach 2
    // Traverse all parents of descendantOne including itself and store in HashSet of
    // AncestralTree. Traverse all parents of descendantTwo including itself and check
    // if it is present in our set then return it otherwise null.
    public static AncestralTree getYoungestCommonAncestor2(
            AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {

        Set<AncestralTree> set = new HashSet<>();
        AncestralTree trav = descendantOne;

        while (trav != null) {
            set.add(trav);
            trav = trav.ancestor;
        }

        trav = descendantTwo;
        while (trav != null) {
            if (set.contains(trav)) {
                return trav;
            }
            trav = trav.ancestor;
        }
        return null;
    }
    // }
    public static void main(String[] args) {

    }
}
