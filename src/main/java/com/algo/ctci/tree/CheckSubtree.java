package com.algo.ctci.tree;

/**
 * Check Subtree:
 * T1 and T2 are two very large binary trees, with T1 much bigger than T2. Create an
 * algorithm to determine if T2 is a subtree of T1.
 */
public class CheckSubtree {
    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int data;
        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // { Approach 1.
    public static boolean containsSubtree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        getPreOrderString(t1, sb1);
        getPreOrderString(t2, sb2);

        return sb1.indexOf(sb2.toString()) != -1;
    }

    private static void getPreOrderString(TreeNode treeNode, StringBuilder sb) {
        if (treeNode == null) {
            sb.append('X');
            return;
        }
        sb.append(treeNode.data);
        getPreOrderString(treeNode.left, sb);
        getPreOrderString(treeNode.right, sb);
    }
    // } -- end of approach 1.
    // { Approach 2
    public static boolean containsSubtree2(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        return isSubtree(t1, t2);
    }

    private static boolean isSubtree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return false;
        } else if (t1.data == t2.data && matchTrees(t1, t2)) {
            return true;
        }
        return isSubtree(t1.left, t2) || isSubtree(t1.right, t2);
    }

    private static boolean matchTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        } else if (t1.data != t2.data) {
            return false;
        } else {
            return matchTrees(t1.left, t2.left) && matchTrees(t1.right, t2.right);
        }
    }
    // } --end of approach 2

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(10, null, null);
        root1.left = new TreeNode(5, null, null);
        root1.right = new TreeNode(20, null, null);
        root1.left.left = new TreeNode(2, null, null);
        root1.left.right = new TreeNode(8, null, null);
        root1.right.left = new TreeNode(15, null, null);
        root1.right.right = new TreeNode(30, null, null);
        root1.right.left.right = new TreeNode(18, null, null);

        TreeNode root2 = new TreeNode(20, null, null);
        root2.left = new TreeNode(15, null, null);
        root2.right = new TreeNode(30, null, null);
        root2.left.right = new TreeNode(18, null, null);

        boolean isContained = containsSubtree(root1, root2);
        if (isContained) {
            System.out.println("Tree 2 is a subtree of tree 1");
        } else {
            System.out.println("Tree 2 is not a subtree of tree 1");
        }

        isContained = containsSubtree2(root1, root2);
        if (isContained) {
            System.out.println("Tree 2 is a subtree of tree 1");
        } else {
            System.out.println("Tree 2 is not a subtree of tree 1");
        }
    }
}
