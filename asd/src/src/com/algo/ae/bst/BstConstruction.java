package com.algo.ae.bst;

/**
 * BST Construction
 *
 * Write a BST class for Binary search tree. The class should support:
 *  - Inserting values with the insert method.
 *  - Removing values with the remove method; this method should only remove
 *    the first instance of a given value.
 *  - Searching for values with the contains method.
 *
 *  Note that you can't remove values from a single-node tree. In other words, calling
 *  the remove() method on a single node tree simply should not do anything.
 *
 *  Each BST node has an integer value, a left child node and a right child
 *  node. A node is said to be a valid BST node, if and only if it satisfies the
 *  BST  property: its value is strictly greater than the values of every node
 *  to its left; its value is less than or equal to the values of every node to
 *  its right; and its children nodes are either valid BST nodes themselves or
 *  None / null.
 */
public class BstConstruction {
    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }

        public BST insert(int value) {
            BST newNode = new BST(value);
            BST curr = this;
            while (curr != null) {
                if (value < curr.value) {
                    if (curr.left == null) {
                        curr.left = newNode;
                        break;
                    }
                    curr = curr.left;
                } else {
                    if (curr.right == null) {
                        curr.right = newNode;
                        break;
                    }
                    curr = curr.right;
                }
            }
            return this;
        }

        public boolean contains(int value) {
            BST curr = this;
            while (curr != null) {
                if (curr.value == value) {
                    return true;
                } else if (value < curr.value) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            return false;
        }

        public BST remove(int value) {
            BST curr = this;
            BST parent = null;

            while (curr != null) {
                if (value < curr.value) {
                    parent = curr;
                    curr = curr.left;
                } else if (value > curr.value) {
                    parent = curr;
                    curr = curr.right;
                } else {
                    // Found the node
                    if (curr.right != null) {
                        curr.copyNextInOrderNodeAndDelete();
                    } else {
                        // value is at root node
                        if (parent == null) {
                            if (curr.left != null) {
                                BST newLeft = curr.left.left;
                                BST newRight = curr.left.right;
                                this.value = curr.left.value;
                                this.left = newLeft;
                                this.right = newRight;
                            }
                        } else {
                            if (parent.left == curr) {
                                parent.left = curr.left;
                            } else {
                                parent.right = curr.left;
                            }
                        }
                    }

                    /*
                    // value is at root node
                    if (parent == null) {
                        if (curr.right != null) {
                            curr.copyNextInOrderNodeAndDelete();
                        } else if (curr.left != null) {
                            BST newLeft = curr.left.left;
                            BST newRight = curr.left.right;
                            this.value = curr.left.value;
                            this.left = newLeft;
                            this.right = newRight;
                        } else {
                            curr = null;
                        }
                    } else {
                        // value is at in intermediate place
                        if (curr.right != null) {
                            curr.copyNextInOrderNodeAndDelete();
                        } else {
                            if (parent.left == curr) {
                                parent.left = curr.left;
                            } else {
                                parent.right = curr.left;
                            }
                        }
                    }*/
                    break;
                }
            }
            return this;
        }

        private void copyNextInOrderNodeAndDelete() {
            BST parent = this;
            BST curr = parent.right;

            while (curr.left != null) {
                parent = curr;
                curr = curr.left;
            }
            this.value = curr.value;
            if (parent.left == curr) {
                parent.left = curr.right;
            } else {
                parent.right = curr.right;
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST(50);
        bst.insert(25);
        bst.insert(100);
        bst.insert(10);
        bst.insert(35);
        bst.insert(80);
        bst.insert(200);
        bst.insert(400);
        bst.insert(90);
        bst.insert(95);
        bst.insert(33);
        bst.insert(32);
        bst.insert(31);
        bst.remove(80);
        bst.remove(31);
        bst.remove(35);
        bst.remove(50);
    }
}
