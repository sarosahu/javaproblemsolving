package com.algo.lc.treegraphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeDeserializeBinaryTree {

    // { Pre-order approach with recursion
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        rserialize(root, sb);
        return sb.toString();
    }

    private void rserialize(TreeNode node, StringBuilder sb) {
        if (sb.length() != 0) {
            sb.append(",");
        }
        if (node == null) {
            sb.append('*');
        } else {
            sb.append(String.valueOf(node.val));
            rserialize(node.left, sb);
            rserialize(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    private TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("*")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }
    // } Pre-order approach with recursion

    // { BFS approach with Queue (level order)
    public String serialize2(TreeNode root) {

        if (root == null) {
            return "";
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node == null) {
                sb.append("#").append(" ");
                continue;
            }

            sb.append(node.val).append(" ");
            q.add(node.left);
            q.add(node.right);
        }

        return sb.toString().trim();
    }

    public TreeNode deserialize2(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] nodes = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int len = nodes.length;
        for (int i = 1; i < len; ++i) {
            TreeNode parent = q.poll();

            if (!nodes[i].equals("#")) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(nodes[i]));
                parent.left = leftNode;
                q.add(leftNode);
            }

            if (!nodes[++i].equals("#")) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(nodes[i]));
                parent.right = rightNode;
                q.add(rightNode);
            }

        }

        return root;
    }
    // } BFS approach with Queue (level order)

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        SerializeDeserializeBinaryTree obj = new SerializeDeserializeBinaryTree();
        String code = obj.serialize(root);
        System.out.println("Code : " + code);

        TreeNode rootNode = obj.deserialize(code);

        String code2 = obj.serialize2(root);
        System.out.println("Code : " + code2);

        TreeNode rootNode2 = obj.deserialize2(code2);

    }
}
