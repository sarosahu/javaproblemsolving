package com.ds.binsearchtree;

public class BinSearchTree<E extends Comparable<E>> {
    // package access members
    private BSTNode<E> root;

    // Ctor initializes an empty tree of integers
    public BinSearchTree() {
        root = null;
    }

    public BSTNode<E> getRoot() {
        return this.root;
    }
    // insert a new node in the BST
    public void insertNode(E insertValue) {
        if (root == null) {
            root = new BSTNode<E>(insertValue);
        } else {
            root.insert(insertValue);
        }
    }

    // begin preorder traversal
    public void preorderTraversal() {
        preorderHelper(root);
    }

    // Recursive method to perform preorder traversal
    private void preorderHelper(BSTNode<E> node) {
        if (node == null) {
            return;
        }

        System.out.printf("%s ", node.data);
        preorderHelper(node.leftNode);
        preorderHelper(node.rightNode);
    }

    public void inorderTraversal() {
        inorderHelper(root);
    }

    // recursive method to perform preorder traversal
    private void inorderHelper(BSTNode<E> node) {
        if (node == null) {
            return;
        }

        inorderHelper(node.leftNode);
        System.out.printf("%s ", node.data);
        inorderHelper(node.rightNode);
    }

    // begin postorder traversal
    public void postorderTraversal() {
        postorderHelper(root);
    }

    private void postorderHelper(BSTNode<E> node) {
        if (node == null) {
            return;
        }

        postorderHelper(node.leftNode);
        postorderHelper(node.rightNode);
        System.out.printf("%s ", node.data);
    }

    public void destroyAllNodes() {
        // TODO
    }
}
