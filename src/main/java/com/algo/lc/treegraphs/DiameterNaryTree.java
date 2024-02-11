package com.algo.lc.treegraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Diameter of N-ary Tree
 *
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
 *
 * The diameter of an N-ary tree is the length of the longest path between any two nodes in
 * the tree. This path may or may not pass through the root.
 *
 * (Nary-Tree input serialization is represented in their level order traversal, each group
 * of children is separated by the null value.)
 *
 * Ex 1:
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: 3
 *
 * Ex 2:
 * Input: root = [1,null,2,null,3,4,null,5,null,6]
 * Output: 4
 *
 * Ex 3:
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: 7
 *
 * Constraints:
 *
 * The depth of the n-ary tree is less than or equal to 1000.
 * The total number of nodes is between [1, 10^4].
 */
public class DiameterNaryTree {
    private int diameter = 0;
    public int diameter(NTreeNode root) {
        height(root);
        return diameter;
    }
    private int height(NTreeNode node) {
        int maxHeight1 = 0, maxHeight2 = 0;
        for (NTreeNode child : node.children) {
            int heightOfChild = height(child) + 1;
            if (heightOfChild > maxHeight1) {
                maxHeight2 = maxHeight1;
                maxHeight1 = heightOfChild;
            } else if (heightOfChild > maxHeight2) {
                maxHeight2 = heightOfChild;
            }
        }
        this.diameter = Math.max(this.diameter,
                (maxHeight1 + maxHeight2));
        return maxHeight1;
    }

    static class NTreeNode {
        int val;
        List<NTreeNode> children = new ArrayList<>();
    }
}
