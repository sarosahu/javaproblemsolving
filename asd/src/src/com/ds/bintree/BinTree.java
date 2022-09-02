package com.ds.bintree;

public class BinTree<E> {
    // package access members
    public BinTreeNode<E> root;

    // Ctor initializes an empty tree of integers
    public BinTree() {
        root = null;
    }

    public BinTreeNode<E> getRoot() {
        return this.root;
    }

    // Insert a new node in the Binary tree.
    // Note: We don't want to insert in a specific case, rather returns the
    // BinTreeNode<E> and caller will place this node into a specific place.
    public BinTreeNode<E> insertNode(E insertValue) {
        return new BinTreeNode<E>(insertValue);
    }

    // begin preorder traversal
    public void preorderTraversal() {
        preorderHelper(root);
    }

    // Recursive method to perform preorder traversal
    private void preorderHelper(BinTreeNode<E> node) {
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
    private void inorderHelper(BinTreeNode<E> node) {
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

    private void postorderHelper(BinTreeNode<E> node) {
        if (node == null) {
            return;
        }

        postorderHelper(node.leftNode);
        postorderHelper(node.rightNode);
        System.out.printf("%s ", node.data);
    }
}
