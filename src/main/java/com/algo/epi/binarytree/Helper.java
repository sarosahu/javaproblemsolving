package com.algo.epi.binarytree;

import com.ds.bintree.BinTree;
import com.ds.bintree.BinTreeNode;

public class Helper {
    public static BinTree<Integer> createBalancedTree() {
        BinTree<Integer> tree = new BinTree<>();
        tree.root = new BinTreeNode<>(1,
                new BinTreeNode<>(2, new BinTreeNode<>(3), new BinTreeNode<>(4)),
                new BinTreeNode<>(5, new BinTreeNode<>(6), new BinTreeNode<>(7)));

        return tree;
    }

    public static BinTree<Integer> createUnBalancedTree() {
        BinTree<Integer> tree = new BinTree<>();
        tree.root = new BinTreeNode<>(1,
                new BinTreeNode<>(2,
                        new BinTreeNode<>(3,
                            new BinTreeNode<>(8,
                                    new BinTreeNode<>(10),
                                    new BinTreeNode<>(11)),
                            new BinTreeNode<>(9)),
                        new BinTreeNode<>(4)),
                new BinTreeNode<>(5, new BinTreeNode<>(6), new BinTreeNode<>(7)));

        return tree;
    }

    public static BinTree<Integer> createBalancedTree2() {
        BinTree<Integer> tree = new BinTree<>();
        tree.root = new BinTreeNode<>(1,
                new BinTreeNode<>(2,
                        new BinTreeNode<>(3,
                                new BinTreeNode<>(8), new BinTreeNode<>(9)),
                        new BinTreeNode<>(4)),
                new BinTreeNode<>(5, new BinTreeNode<>(6), new BinTreeNode<>(7)));

        return tree;
    }
}
