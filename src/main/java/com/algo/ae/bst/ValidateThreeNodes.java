package com.algo.ae.bst;

/**
 * Validate Three Nodes
 *
 * You're given 3 nodes that are contained in the same Binary Search Tree: nodeOne,
 * nodeTwo, and nodeThree. Write a function that returns a boolean representing
 * whether one of nodeOne or nodeThree is an ancestor of nodeTwo and other node
 * is a descendant of nodeTwo. For example, if your function determines that
 * nodeOne is an ancestor of nodeTwo, then it needs to see if nodeThree is a
 * descendant of nodeTwo. If your function determines that nodeThree is an
 * ancestor, then it needs to see if nodeOne is a descendant.
 *
 * A descendant of a node N is defined as a node contained in the tree rooted
 * at N. A node N is an ancestor of another node M if M is a descendant to N.
 *
 * It isn't guaranteed that nodeOne or nodeThree will be ancestors or descendants
 * of nodeTwo, but it is guaranteed that all 3 nodes will be unique and will
 * never be None/null. In other words, you'll be given valid input nodes.
 *
 * Each BST node has an integer value, a left child node and a right child
 * node. A node is said to be a valid BST node, if and only if it satisfies the
 * BST  property: its value is strictly greater than the values of every node
 * to its left; its value is less than or equal to the values of every node to
 * its right; and its children nodes are either valid BST nodes themselves or
 * None / null.
 */
public class ValidateThreeNodes {
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    // Time: O(h), Space : O(1)
    public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
        // Write your code here.
        // I referred the solution.
        if (isDescendant(nodeTwo, nodeThree)) {
            return isDescendant(nodeOne, nodeTwo);
        }

        if (isDescendant(nodeTwo, nodeOne)) {
            return isDescendant(nodeThree, nodeTwo);
        }
        return false;
    }

    // return true if target is descendant of node
    private boolean isDescendant(BST node, BST target) {
        while (node != null && node != target) {
            if (node.value <= target.value) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return node == target;
    }

    // Time: O(d) and space : O(1), d is distance between nodeOne and nodeThree.
    public boolean validateThreeNodesE(BST nodeOne, BST nodeTwo, BST nodeThree) {
        // >>>>>> This is better code <<<<<<
        // This is little bit trick, but far better solution then others.
        BST searchOne = nodeOne;
        BST searchThree = nodeThree;

        boolean foundThreeFromOne = false;
        boolean foundOneFromThree = false;
        boolean foundNodeTwo = false;
        while (true) {
            foundThreeFromOne = searchOne == nodeThree;
            foundOneFromThree = searchThree == nodeOne;
            foundNodeTwo = (searchOne == nodeTwo) || (searchThree == nodeTwo);
            boolean finishedSearching = (searchOne == null) && (searchThree == null);

            if (foundThreeFromOne || foundOneFromThree || foundNodeTwo || finishedSearching) {
                break;
            }

            if (searchOne != null) {
                searchOne = searchOne.value > nodeTwo.value ? searchOne.left : searchOne.right;
            }

            if (searchThree != null) {
                searchThree = searchThree.value > nodeTwo.value ? searchThree.left : searchThree.right;
            }
        }

        if (!foundNodeTwo || foundThreeFromOne || foundOneFromThree) {
            return false;
        }
        return searchForTarget(nodeTwo, (searchOne == nodeTwo) ? nodeThree : nodeOne);
    }

    public boolean searchForTarget(BST node, BST target) {
        while (node != null && node != target) {
            node = (target.value < node.value) ? node.left : node.right;
        }

        return node == target;
    }
}
