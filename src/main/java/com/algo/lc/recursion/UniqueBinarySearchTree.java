package com.algo.lc.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 *
 * Example : 1
 *
 * Input : n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 *
 *      1         1           2             3             3
 *       \         \        /  \           /            /
 *        3         2      1    3         2            1
 *      /            \                   /              \
 *     2              3                 1                2
 *
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class UniqueBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {}
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<TreeNode> allPossibleBST(int start, int end, Map<Integer, List<TreeNode>> memo) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        int hashKey = start * 10 + end;
        if (memo.containsKey(hashKey)) {
            return memo.get(hashKey);
        }

        // Iterate through all values from start to end to construct left and right subtree recursively.
        for (int i = start; i <= end; ++i) {
            List<TreeNode> leftSubtrees = allPossibleBST(start, i - 1, memo);
            List<TreeNode> rightSubtrees = allPossibleBST(i + 1, end, memo);

            // Loop through all left and right subtrees and connect them to ith root.
            for (TreeNode left : leftSubtrees) {
                for (TreeNode right : rightSubtrees) {
                    TreeNode root = new TreeNode(i, left, right);
                    res.add(root);
                }
            }
        }
        memo.put(hashKey, res);
        return res;
    }

    public List<TreeNode> generateBSTs(int n) {
        Map<Integer, List<TreeNode>> memo = new HashMap<>();
        return allPossibleBST(1, n, memo);
    }

    // So far this approach looks to be bit complicated to me
    // The above recursive approach looks simple to understand.
    public List<TreeNode> generateBSTsBottomUp(int n) {
        List<List<TreeNode>> dp = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add(null);

        for (int numberOfNodes = 1; numberOfNodes <= n; numberOfNodes++) {
            for (int i = 1; i <= numberOfNodes; i++) {
                int j = numberOfNodes - i;
                for (TreeNode left : dp.get(i - 1)) {
                    for (TreeNode right : dp.get(j)) {
                        TreeNode root = new TreeNode(i, left, clone(right, i));
                        dp.get(numberOfNodes).add(root);
                    }
                }
            }
        }
        return dp.get(n);
    }

    private TreeNode clone(TreeNode node, int offset) {
        if (node == null) {
            return null;
        }
        TreeNode clonedNode = new TreeNode(node.val + offset);
        clonedNode.left = clone(node.left, offset);
        clonedNode.right = clone(node.right, offset);
        return clonedNode;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTree obj = new UniqueBinarySearchTree();
        List<TreeNode> listOfBst = obj.generateBSTs(3);
        // listOfBst = obj.generateBSTsBottomUp(3);
    }
}
