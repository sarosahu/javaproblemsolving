package com.algo.ae.graph;

public class YoungestCommonAncestor {
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
    public static void main(String[] args) {

    }
}
