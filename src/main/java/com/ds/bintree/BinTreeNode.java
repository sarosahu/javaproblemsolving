package com.ds.bintree;

public class BinTreeNode<E> {
    // package access members
    public E data;
    public BinTreeNode<E> leftNode, rightNode;

    // Ctor initializes data and makes this a leaf node
    public BinTreeNode(E data) {
        this.data = data;
        leftNode = rightNode = null; // node has no children
    }

    public BinTreeNode(E data, BinTreeNode<E> leftNode, BinTreeNode<E> rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public E getData() {
        return this.data;
    }
    public void setData(E data) {
        this.data = data;
    }
    public BinTreeNode<E> getLeftNode() {
        return this.leftNode;
    }
    public BinTreeNode<E> getRightNode() {
        return this.rightNode;
    }
}
