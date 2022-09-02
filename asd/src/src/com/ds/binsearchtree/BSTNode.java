package com.ds.binsearchtree;

public class BSTNode<E extends Comparable<E>> {
    // package access members
    public E data;
    public BSTNode<E> leftNode, rightNode;

    // Ctor initializes data and makes this a leaf node
    public BSTNode(E nodeData) {
        data = nodeData;
        leftNode = rightNode = null; // node has no children
    }

    public E getData() {
        return this.data;
    }
    public BSTNode<E> getLeftNode() {
        return this.leftNode;
    }
    public BSTNode<E> getRightNode() {
        return this.rightNode;
    }
    // locate insertion point and insert new node;
    // ignore duplicate values
    public void insert(E insertValue) {
        // insert in left subtree
        if (insertValue.compareTo(data) < 0) {
            // insert new BSTNode
            if (leftNode == null) {
                leftNode = new BSTNode<>(insertValue);
            } else {
                leftNode.insert(insertValue);
            }
        }
        // insert in right subtree
        else if (insertValue.compareTo(data) > 0) {
            if (rightNode == null) {
                rightNode = new BSTNode<>(insertValue);
            } else {
                rightNode.insert(insertValue);
            }
        }
    }
}
