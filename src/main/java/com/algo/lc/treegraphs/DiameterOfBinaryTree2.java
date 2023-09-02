package com.algo.lc.treegraphs;

import java.util.ArrayList;
import java.util.List;

public class DiameterOfBinaryTree2 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static class NodeInfo {
        TreeNode currNode;
        NodeInfo parent;
        int depth;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        NodeInfo info = new NodeInfo();
        List<NodeInfo> leafs = new ArrayList<>();
        storeNodeInfo(info, root, null, 0, leafs);
        return computeMaxDiameter(leafs);
    }

    public void storeNodeInfo(NodeInfo infoTree,
                              TreeNode currNode,
                              NodeInfo parent,
                              int depth, List<NodeInfo> leafs) {
        infoTree.currNode = currNode;
        infoTree.parent = parent;
        infoTree.depth = depth;
        if (currNode.left == null && currNode.right == null) {
            leafs.add(infoTree);
            return;
        }
        if (currNode.left != null) {
            NodeInfo left = new NodeInfo();
            storeNodeInfo(left, currNode.left, infoTree, depth + 1, leafs);
        }
        if (currNode.right != null) {
            NodeInfo right = new NodeInfo();
            storeNodeInfo(right, currNode.right, infoTree, depth + 1, leafs);
        }
    }

    public int computeMaxDiameter(List<NodeInfo> leafs) {
        if (leafs.size() == 1) {
            return leafs.get(0).depth;
        }
        int maxDia = Integer.MIN_VALUE;
        for (int i = 0; i < leafs.size(); ++i) {
            NodeInfo first = leafs.get(i);
            for (int j = i + 1; j < leafs.size(); ++j) {
                NodeInfo second = leafs.get(j);
                int diameter = computeDiameter(first, second);
                maxDia = Math.max(diameter, maxDia);
            }
        }
        return maxDia;
    }

    public int computeDiameter(NodeInfo one, NodeInfo two) {
        int diff = one.depth - two.depth;
        if (diff < 0) {
            diff *= -1;
        }
        int diameter = diff;
        NodeInfo larger = one.depth > two.depth ? one : two;
        int depthOfLarger = larger.depth;
        NodeInfo smaller = larger == one ? two : one;
        while (diff > 0) {
            larger = larger.parent;
            --diff;
        }
        while (larger != smaller) {
            larger = larger.parent;
            smaller = smaller.parent;
            diameter += 2;
        }
        return diameter > depthOfLarger ? diameter : depthOfLarger;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2, null, null);
        root.left = new TreeNode(5, null, null);
        root.left.left = new TreeNode(3, null, null);
        root.left.left.left = new TreeNode(1, null, null);
        root.left.left.right = new TreeNode(4, null, null);

        DiameterOfBinaryTree2 obj = new DiameterOfBinaryTree2();
        int diameter = obj.diameterOfBinaryTree(root);
        System.out.println("Diameter : " + diameter);
    }
}
