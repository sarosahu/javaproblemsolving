package com.algo.lc.treegraphs;

import java.util.*;

public class ValidateBinSearchTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class TreeNodeLimits {
        TreeNode node;
        Integer min;
        Integer max;
        TreeNodeLimits(TreeNode node, Integer min, Integer max) {
            this.node = node;
            this.min = min;
            this.max = max;
        }
    }

    Deque<TreeNode> stack = new LinkedList<>();
    Deque<Integer> upperLimits = new LinkedList<>();
    Deque<Integer> lowerLimits = new LinkedList<>();

    Integer prev = null;

    // { Approach 1 : Recursive
    /**
     * Complexity Analysis
     *
     * Time complexity : O(N) since we visit each node exactly once.
     * Space complexity : O(N) since we keep up to the entire tree.
     */
    public boolean isValidBSTR(TreeNode root) {
        return isValidBSTRHelper(root, null, null);
    }

    private boolean isValidBSTRHelper(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        return isValidBSTRHelper(root.left, low, root.val) && isValidBSTRHelper(root.right, root.val, high);
    }
    // }

    /**
     * Approach 2: Iterative Traversal with Valid Range
     * The above recursion could be converted into iteration, with the help of an explicit stack.
     * DFS would be better than BFS since it works faster here.
     *
     * Complexity Analysis
     *
     * Time complexity : O(N) since we visit each node exactly once.
     * Space complexity : O(N) since we keep up to the entire tree.
     */

    public boolean isValidBSTi(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        update(root, null, null);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.poll();
            Integer low = lowerLimits.poll();
            Integer high = upperLimits.poll();

            if (curr == null) {
                continue;
            }
            //Integer val = curr.val;

            if (low != null && curr.val <= low.intValue()) {
                return false;
            }
            if (high != null && curr.val >= high.intValue()) {
                return false;
            }
            update(curr.right, curr.val, high);
            update(curr.left, low, curr.val);
        }
        return true;
    }

    private void update(TreeNode node,
                        Integer low,
                        Integer high) {
        stack.add(node);
        lowerLimits.add(low);
        upperLimits.add(high);
    }
    // >>>>>> isValidBSTi() done

    /**
     * Approach 3: Iterative inorder: using stack
     * Complexity Analysis
     *
     * Time complexity : O(N) in the worst case
     * when the tree is BST or the "bad" element is a rightmost leaf.
     *
     * Space complexity : O(N) to keep stack.
     */
    public boolean isValidBSTInOrderTraversal(TreeNode root) {
        Integer prev = null;
        Stack<TreeNode> stk = new Stack<>();
        while (root != null || !stk.isEmpty()) {
            if (root != null) {
                stk.push(root);
                root = root.left;
            } else {
                root = stk.pop();
                if (prev != null && prev >= root.val) {
                    return false;
                }
                prev = root.val;
                root = root.right;
            }
        }
        return true;
    }
    // isValidBSTInOrderTraversal >>>> Done.

    /**
     * Approach 3: Recursive inorder
     * Complexity Analysis
     *
     * Time complexity : O(N) in the worst case
     * when the tree is BST or the "bad" element is a rightmost leaf.
     *
     * Space complexity : O(N) to keep stack.
     */
    public boolean isValidBSTInOrderTraversalR(TreeNode root) {
        return inorderR(root);
    }

    private boolean inorderR(TreeNode curr) {
        if (curr == null) {
            return true;
        }
        if (!inorderR(curr.left)) {
            return false;
        }
        if (prev != null && curr.val <= prev.intValue()) {
            return false;
        }
        prev = curr.val;
        return inorderR(curr.right);
    }
    // isValidBSTInOrderTraversalR >>>> Done.

    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        Queue<TreeNodeLimits> queue = new LinkedList<>();
        //queue.add(new TreeNodeLimits(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        queue.add(new TreeNodeLimits(root, null, null));

        while (!queue.isEmpty()) {
            TreeNodeLimits curr = queue.poll();
            if (curr.min != null && curr.node.val <= curr.min ||
                    curr.max != null && curr.node.val >= curr.max) {
                return false;
            }
            TreeNode leftNode = curr.node.left;
            if (leftNode != null) {
                queue.add(new TreeNodeLimits(leftNode, curr.min, curr.node.val));
            }

            TreeNode rightNode = curr.node.right;
            if (rightNode != null) {
                queue.add(new TreeNodeLimits(rightNode, curr.node.val, curr.max));
            }
        }
        return true;
    }

    private static void addToBST(TreeNode root, int num) {
        TreeNode curr = root;
        if (curr.val < num) {
            if (curr.right != null) {
                addToBST(curr.right, num);
            } else {
                curr.right = new TreeNode(num);
                return;
            }
        } else {
            if (curr.left != null) {
                addToBST(curr.left, num);
            } else {
                curr.left = new TreeNode(num);
                return;
            }
        }
    }
    public static TreeNode
    createBST(int[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            int currNum = nums[i];
            addToBST(root, currNum);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] nums1 = {100, 50, 500, 20, 80, 200, 700, 5, 30, 60, 90, 150, 400, 600, 800};
        int[] nums2 = {0, -1};
        List<int[]> listOfArray = new ArrayList<>();
        listOfArray.add(nums1);
        listOfArray.add(nums2);
        for (int[] nums : listOfArray) {
            System.out.println("Tree with numbers : " + Arrays.toString(nums));
            TreeNode root = createBST(nums);
            ValidateBinSearchTree obj = new ValidateBinSearchTree();
            boolean isValid = obj.isValidBSTR(root);
            if (isValid) {
                System.out.println("Valid BST");
            } else {
                System.out.println("Invalid BST");
            }

            isValid = obj.isValidBST(root);
            if (isValid) {
                System.out.println("Valid BST");
            } else {
                System.out.println("Invalid BST");
            }

            isValid = obj.isValidBSTInOrderTraversal(root);
            if (isValid) {
                System.out.println("Valid BST");
            } else {
                System.out.println("Invalid BST");
            }

            isValid = obj.isValidBSTi(root);
            if (isValid) {
                System.out.println("Valid BST");
            } else {
                System.out.println("Invalid BST");
            }

            isValid = obj.isValidBSTInOrderTraversalR(root);
            if (isValid) {
                System.out.println("Valid BST");
            } else {
                System.out.println("Invalid BST");
            }
        }
    }
}
