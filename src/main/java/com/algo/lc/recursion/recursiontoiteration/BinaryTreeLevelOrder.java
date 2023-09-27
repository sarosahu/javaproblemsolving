package com.algo.lc.recursion.recursiontoiteration;

import com.algo.util.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given the root of a binary tree, return the level order traversal
 * of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 *              3
 *            /  \
 *           9    20
 *               /  \
 *              15   7
 */
public class BinaryTreeLevelOrder {
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

    // Recursive approach, Time: O(N), space: O(N)
    public List<List<Integer>> levelOrderR(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }
        levelOrderHelper(levels, root, 0);
        return levels;
    }

    private void levelOrderHelper(List<List<Integer>> levels,
                                  TreeNode node,
                                  int levelNum)
    {
        if (levels.size() == levelNum) {
            levels.add(new ArrayList<>());
        }

        levels.get(levelNum).add(node.val);

        // process children starting from left
        if (node.left != null) {
            levelOrderHelper(levels, node.left, levelNum + 1);
        }
        if (node.right != null) {
            levelOrderHelper(levels, node.right, levelNum + 1);
        }
    }

    // Iterative approach using Queue, Time: O(N), Space: O(N)
    public List<List<Integer>> levelOrderL(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currLevelSize = queue.size();
            List<Integer> currLevelList = new ArrayList<>();
            while (currLevelSize > 0) {
                TreeNode currNode = queue.poll();
                currLevelList.add(currNode.val);
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
                --currLevelSize;
            }
            answer.add(currLevelList);
        }
        return answer;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50,
                new TreeNode(30, new TreeNode(10), new TreeNode(40)),
                new TreeNode(80, new TreeNode(60), new TreeNode(100))
        );
        BinaryTreeLevelOrder obj = new BinaryTreeLevelOrder();
        List<List<Integer>> levelList = obj.levelOrderR(root);
        Utils.print2DList(levelList);
        System.out.println();
        List<List<Integer>> levelList2 = obj.levelOrderL(root);
        Utils.print2DList(levelList2);
    }
}
