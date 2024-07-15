package com.algo.lc.treegraphs;

/**
 * 687. Longest Univalue Path
 *
 * Given the root of a binary tree, return the length of the longest path,
 * where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 * E.g. 1
 * Input: root = [5,4,5,1,1,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 *
 * E.g 2
 * Input: root = [1,4,5,4,4,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 4).
 */
public class LongestUnivaluePath {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private int ans;
    public int longestUnivaluePath(TreeNode root) {
        helper(root, null);
        return ans;
    }

    private int helper(TreeNode node, TreeNode parentNode) {
        if (node == null) {
            return 0;
        }

        int left = helper(node.left, node);
        int right = helper(node.right, node);

        // The longest univalue path will cover nodes on both sides of the root
        this.ans = Math.max(ans, left + right);

        // For root node, parent is null, hence we can return 0
        // Note: In this case the above line already set the final answer(this.ans)
        if (parentNode == null) {
            return 0;
        }
        // The number of nodes will be zero if the root value isn't equal to the root.
        // Otherwise, return the max of left and right nodes plus one for the node itself.
        return node.val == parentNode.val ? Math.max(left, right) + 1 : 0;
    }
}
