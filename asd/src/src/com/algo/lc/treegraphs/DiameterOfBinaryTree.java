package com.algo.lc.treegraphs;

import com.ds.bintree.BinTreeNode;

import java.util.ArrayList;
import java.util.List;

public class DiameterOfBinaryTree {
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

    // Time : O(N), Space : O(N)
    public int diameterOfBinaryTree(BinTreeNode<Integer> root) {
        return getTreeInfo(root).diameter;
    }

    public List<Integer>
    pathOfDiameterOfBinaryTree(BinTreeNode<Integer> root) {
        return getTreeInfo(root).diameterPath;
    }

    public TreeInfo getTreeInfo(BinTreeNode<Integer> node) {
        if (node == null) {
            return new TreeInfo(0, 0);
        }

        TreeInfo leftResult = getTreeInfo(node.leftNode);
        TreeInfo rightResult = getTreeInfo(node.rightNode);

        int longestPathThroughThisNode = leftResult.height + rightResult.height;
        int maxDiameterSoFar = Math.max(leftResult.diameter, rightResult.diameter);
        int currDiameter = Math.max(longestPathThroughThisNode, maxDiameterSoFar);

        TreeInfo currInfo = new TreeInfo();
        /*
         * TODO: This logic doesn't work to process path --
         *  Hence commenting for now.
        if (longestPathThroughThisNode > maxDiameterSoFar) {
            currInfo.diameterPath.addAll(leftResult.diameterPath);
            currInfo.diameterPath.add(node.data);
            currInfo.diameterPath.addAll(rightResult.diameterPath);
        } else if (leftResult.diameter > rightResult.diameter) {
            currInfo.diameterPath.addAll(leftResult.diameterPath);
            currInfo.diameterPath.add(node.data);
        } else {
            currInfo.diameterPath.add(node.data);
            currInfo.diameterPath.addAll(rightResult.diameterPath);
        }
         */
        int currHeight = Math.max(leftResult.height, rightResult.height) + 1;
        currInfo.diameter = currDiameter;
        currInfo.height = currHeight;
        return currInfo;
        //return new TreeInfo(currDiameter, currHeight);
    }

    public static void main(String[] args) {
        BinTreeNode<Integer> root = new BinTreeNode<>(1);
        root.leftNode = new BinTreeNode<>(3);
        root.rightNode = new BinTreeNode<>(2);
        root.leftNode.leftNode = new BinTreeNode<>(7);
        root.leftNode.leftNode.leftNode = new BinTreeNode<>(8);
        root.leftNode.leftNode.leftNode.leftNode = new BinTreeNode<>(9);

        root.leftNode.rightNode = new BinTreeNode<>(4);
        //root.leftNode.rightNode.rightNode = new BinTreeNode<>(5);
        //root.leftNode.rightNode.rightNode.rightNode = new BinTreeNode<>(6);

        DiameterOfBinaryTree obj = new DiameterOfBinaryTree();
        int diameter = obj.diameterOfBinaryTree(root);
        System.out.println("Diameter : " + diameter);

        /*
         * TODO : Once the above logic works, then will uncomment this.
        List<Integer> path = obj.pathOfDiameterOfBinaryTree(root);
        System.out.println("Path of diameter : ");
        for (int d : path) {
            System.out.printf("%d - ", d);
        }
        System.out.println();
         */
    }
}
