package com.algo.lc.treegraphs;

import com.ds.bintree.BinTreeNode;

public class DiameterOfBinaryTree {
    static class TreeInfo {
        int diameter;
        int height;
        public TreeInfo(int d, int h) {
            this.diameter = d;
            this.height = h;
        }
    }

    public int diameterOfBinaryTree(BinTreeNode<Integer> root) {
        return getTreeInfo(root).diameter;
    }

    public TreeInfo getTreeInfo(BinTreeNode<Integer> node) {
        if (node == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo leftResult = getTreeInfo(node.leftNode);
        TreeInfo rightResult = getTreeInfo(node.rightNode);

        int longestPathThroughRoot = leftResult.height + rightResult.height;
        int maxDiameterSoFar = Math.max(leftResult.diameter, rightResult.diameter);
        int currDiameter = Math.max(longestPathThroughRoot, maxDiameterSoFar);
        int currHeight = Math.max(leftResult.height, rightResult.height) + 1;

        return new TreeInfo(currDiameter, currHeight);
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

        DiameterOfBinaryTree obj = new DiameterOfBinaryTree();
        int diameter = obj.diameterOfBinaryTree(root);
        System.out.println("Diameter : " + diameter);
    }
}
