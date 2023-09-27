package com.algo.lc.recursion.recursiontoiteration;

import com.algo.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInOrderTraversal {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Recursive approach
    // Time: O(n), space: O(n)
    public List<Integer> inorderTraversalR(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorderTraversalHelper(ans, root);
        return ans;
    }

    // Iterative approach using Stack
    // Time: O(n), space: O(n)
    public List<Integer> inorderTraversalI1(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.peek();
                stack.pop();
                ans.add(curr.val);
                curr = curr.right;
            }
        }
        return ans;
    }

    // Iterative approach using Morris traversal using threaded binary tree
    // Time: O(n), space: O(1)
    public List<Integer> inorderTraversalI2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode pre = curr.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = curr;
                TreeNode prev = curr;
                curr = curr.left;
                prev.left = null;
            } else {
                ans.add(curr.val);
                curr = curr.right;
            }
        }
        return ans;
    }

    private void inorderTraversalHelper(List<Integer> ans, TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversalHelper(ans, node.left);
        ans.add(node.val);
        inorderTraversalHelper(ans, node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50,
                new TreeNode(30, new TreeNode(10), new TreeNode(40)),
                new TreeNode(80, new TreeNode(60), new TreeNode(100))
        );
        BinaryTreeInOrderTraversal obj = new BinaryTreeInOrderTraversal();

        List<Integer> res1 = obj.inorderTraversalR(root);
        Utils.print1DArray(res1.stream().toArray(Integer[]::new));

        List<Integer> res2 = obj.inorderTraversalI1(root);
        Utils.print1DArray(res2.stream().toArray(Integer[]::new));

        List<Integer> res3 = obj.inorderTraversalI2(root);
        Utils.print1DArray(res3.stream().toArray(Integer[]::new));
    }
}
