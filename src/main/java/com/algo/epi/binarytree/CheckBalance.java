package com.algo.epi.binarytree;

import com.ds.bintree.BinTree;
import com.ds.bintree.BinTreeNode;

/**
 * EPI : Binary tree - 10.1 - To check if a binary tree is a balance tree
 */
public class CheckBalance {
    boolean isBalanced(BinTree<Integer> tree) {
        BinTreeNode<Integer> root = tree.root;
        return checkBalance(root).balanced;
    }

    private TreeInfo checkBalance(BinTreeNode<Integer> root) {
        if (root == null) {
            return new TreeInfo(true, -1);
        }
        TreeInfo leftResult = checkBalance(root.leftNode);
        if (!leftResult.balanced) {
            return new TreeInfo(false, 0);
        }
        TreeInfo rightResult = checkBalance(root.rightNode);
        if (!rightResult.balanced) {
            return new TreeInfo(false, 0);
        }
        boolean balanced = Math.abs(leftResult.height - rightResult.height) <= 1;
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        return new TreeInfo(balanced, height);
    }

    static class TreeInfo {
        boolean balanced;
        int height;
        TreeInfo(boolean balanced, int height) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        BinTree<Integer> tree = Helper.createBalancedTree();
        CheckBalance obj = new CheckBalance();
        boolean isBalanced = obj.isBalanced(tree);
        if (isBalanced) {
            System.out.println("Tree(balanced) is balanced.");
        } else {
            System.out.println("Tree(balanced) is unbalanced.");
        }

        BinTree<Integer> tree2 = Helper.createBalancedTree2();
        isBalanced = obj.isBalanced(tree2);
        if (isBalanced) {
            System.out.println("Tree2(balanced) is balanced.");
        } else {
            System.out.println("Tree2(balanced) is unbalanced.");
        }

        BinTree<Integer> tree3 = Helper.createUnBalancedTree();
        isBalanced = obj.isBalanced(tree3);
        if (isBalanced) {
            System.out.println("Tree3(unbalanced) is balanced.");
        } else {
            System.out.println("Tree3(unabalanced) is unbalanced.");
        }
    }
}
