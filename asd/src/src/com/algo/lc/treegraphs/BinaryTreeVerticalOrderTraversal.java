package com.algo.lc.treegraphs;

import com.ds.bintree.BinTreeNode;

import java.util.*;

/**
 * Given the root of a binary tree, return the vertical order
 * traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 *
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 *
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 */
public class BinaryTreeVerticalOrderTraversal {
    static class TreeNodeInfo {
        BinTreeNode<Integer> node;
        int columnIndex;
        public TreeNodeInfo(BinTreeNode<Integer> node, int columnIndex) {
            this.node = node;
            this.columnIndex = columnIndex;
        }
    }
    public List<List<Integer>>
    verticalOrder(BinTreeNode<Integer> root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        Map<Integer, List<Integer>>  columnTable = new HashMap<>();
        Queue<TreeNodeInfo> queue = new ArrayDeque<>();
        queue.offer(new TreeNodeInfo(root, 0));
        while (!queue.isEmpty()) {
            TreeNodeInfo curr = queue.poll();
            BinTreeNode<Integer> currNode = curr.node;
            int currColumnIndex = curr.columnIndex;
            if (!columnTable.containsKey(currColumnIndex)) {
                columnTable.put(currColumnIndex, new ArrayList<>());
            }
            columnTable.get(currColumnIndex).add(currNode.data);
            if (currNode.leftNode != null) {
                queue.offer(new TreeNodeInfo(currNode.leftNode, currColumnIndex - 1));
            }
            if (currNode.rightNode != null) {
                queue.offer(new TreeNodeInfo(currNode.rightNode, currColumnIndex + 1));
            }
        }
        List<Integer> sortedKeys = new ArrayList<>(columnTable.keySet());
        Collections.sort(sortedKeys);
        for (int k : sortedKeys) {
            output.add(columnTable.get(k));
        }
        return output;
    }

    public List<List<Integer>>
    verticalOrderWithoutSort(BinTreeNode<Integer> root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        Map<Integer, List<Integer>>  columnTable = new HashMap<>();
        Queue<TreeNodeInfo> queue = new ArrayDeque<>();
        queue.offer(new TreeNodeInfo(root, 0));
        int minColumn = 0, maxColumn = 0;
        while (!queue.isEmpty()) {
            TreeNodeInfo curr = queue.poll();
            BinTreeNode<Integer> currNode = curr.node;
            int currColumnIndex = curr.columnIndex;
            minColumn = Math.min(currColumnIndex, minColumn);
            maxColumn = Math.max(currColumnIndex, maxColumn);
            if (!columnTable.containsKey(currColumnIndex)) {
                columnTable.put(currColumnIndex, new ArrayList<>());
            }
            columnTable.get(currColumnIndex).add(currNode.data);
            if (currNode.leftNode != null) {
                queue.offer(new TreeNodeInfo(currNode.leftNode, currColumnIndex - 1));
            }
            if (currNode.rightNode != null) {
                queue.offer(new TreeNodeInfo(currNode.rightNode, currColumnIndex + 1));
            }
        }

        for (int i = minColumn; i <= maxColumn; ++i) {
            output.add(columnTable.get(i));
        }
        return output;
    }

    public static void main(String[] args) {
        BinTreeNode<Integer> root = new BinTreeNode<>(1);
        root.leftNode = new BinTreeNode<>(3);
        root.rightNode = new BinTreeNode<>(2);

        root.leftNode.rightNode = new BinTreeNode<>(4);
        root.rightNode.leftNode = new BinTreeNode<>(5);

        BinaryTreeVerticalOrderTraversal obj = new BinaryTreeVerticalOrderTraversal();
        List<List<Integer>> output = obj.verticalOrderWithoutSort(root);
        for (List<Integer> columnData : output) {
            for (int data : columnData) {
                System.out.printf("%d ", data);
            }
            System.out.println();
        }
    }
}
