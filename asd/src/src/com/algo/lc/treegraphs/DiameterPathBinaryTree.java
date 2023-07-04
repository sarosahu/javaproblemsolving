package com.algo.lc.treegraphs;

import com.ds.bintree.BinTreeNode;

import java.util.ArrayList;
import java.util.List;

public class DiameterPathBinaryTree {
    static class TreeInfo {
        int diameter;
        int height;
        List<Integer> diameterPath = new ArrayList<>();
        public TreeInfo() {
        }

        public TreeInfo(int d, int h) {
            this.diameter = d;
            this.height = h;
        }
    }
    private int diameter;
    private List<Integer> path;
    private List<Integer> maxPath;

    // Time : O(N), Space : O(N)
    public int diameterOfBinaryTree(BinTreeNode<Integer> root) {
        diameter = 0;
        path = new ArrayList<>();
        maxPath = new ArrayList<>();
        int longestPath = processLongestPath(root);
        System.out.println("Longest path : " + longestPath);
        return diameter;
    }

    /**
     *
     * @param root
     * @return
     * TODO: maxPath is still incorrect.
     */
    public int processLongestPath(BinTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        path.add(root.data);
        int left = processLongestPath(root.leftNode);
        int right = processLongestPath(root.rightNode);

        if (left + right > diameter) {
            diameter = left + right;
            maxPath = new ArrayList<>(path);
        }

        if (left != 0 && right != 0) {
            path.remove(path.size() - 1);
        }

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        BinTreeNode<Integer> root = new BinTreeNode<>(1);
        root.leftNode = new BinTreeNode<>(3);
        root.rightNode = new BinTreeNode<>(2);
        root.leftNode.leftNode = new BinTreeNode<>(7);
        root.leftNode.leftNode.leftNode = new BinTreeNode<>(8);
        root.leftNode.leftNode.leftNode.leftNode = new BinTreeNode<>(9);

        root.leftNode.rightNode = new BinTreeNode<>(4);
        root.leftNode.rightNode.rightNode = new BinTreeNode<>(5);
        root.leftNode.rightNode.rightNode.rightNode = new BinTreeNode<>(6);

        root.rightNode.rightNode = new BinTreeNode<>(10);
        root.rightNode.rightNode.rightNode = new BinTreeNode<>(11);

        DiameterPathBinaryTree obj = new DiameterPathBinaryTree();
        int diameter = obj.diameterOfBinaryTree(root);
        System.out.println("Diameter : " + diameter);

        System.out.println("Path of diameter : ");
        for (int d : obj.maxPath) {
            System.out.printf("%d - ", d);
        }
        System.out.println();
    }
}
