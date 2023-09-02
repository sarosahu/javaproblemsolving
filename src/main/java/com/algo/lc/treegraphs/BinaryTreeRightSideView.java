package com.algo.lc.treegraphs;

import com.ds.bintree.BinTreeNode;

import java.util.*;

public class BinaryTreeRightSideView {
    // Approach 3: BFS: One Queue + Level Size Measurements
    public List<Integer>
    rightSideView2(BinTreeNode<Integer> root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> rightSide = new ArrayList<>();
        Deque<BinTreeNode<Integer>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelLength = queue.size();

            for (int i = 0; i < levelLength; ++i) {
                BinTreeNode<Integer> curr = queue.poll();
                if (i == 0) {
                    rightSide.add(curr.data);
                }

                if (curr.rightNode != null) {
                    queue.add(curr.rightNode);
                }
                if (curr.leftNode != null) {
                    queue.add(curr.leftNode);
                }
            }
        }
        return rightSide;
    }

    //Approach 2: BFS: One Queue + Sentinel
    public List<Integer> rightSideView(BinTreeNode<Integer> root) {
        if (root == null) {
            return new ArrayList<>();
        }

        Queue<BinTreeNode<Integer>> queue = new LinkedList<>() {
            {
                offer(root);
                offer(null);
            }
        };
        BinTreeNode<Integer> prev, curr = root;
        List<Integer> rightSide = new ArrayList<>();

        while (!queue.isEmpty()) {
            prev = curr;
            curr = queue.poll();

            while (curr != null) {
                // Add child nodes in the queue
                if (curr.leftNode != null) {
                    queue.offer(curr.leftNode);
                }
                if (curr.rightNode != null) {
                    queue.offer(curr.rightNode);
                }

                prev = curr;
                curr = queue.poll();
            }

            // the current level is finished
            // and the prev is its rightmost element
            rightSide.add(prev.data);

            // Add a sentinel to mark the end
            // of the next level
            if (!queue.isEmpty()) {
                queue.offer(null);
            }
        }
        return rightSide;
    }

    public static void main(String[] args) {
        BinTreeNode<Integer> btreeNode = new BinTreeNode<>(1);
        btreeNode.leftNode = new BinTreeNode<>(2);
        btreeNode.rightNode = new BinTreeNode<>(3);
        btreeNode.leftNode.rightNode = new BinTreeNode<>(10);

        BinaryTreeRightSideView obj = new BinaryTreeRightSideView();
        List<Integer> rightSides = obj.rightSideView(btreeNode);
        for (int num : rightSides) {
            System.out.printf("%d -> ", num);
        }
        System.out.println("NULL");

        List<Integer> rightSides2 = obj.rightSideView2(btreeNode);
        for (int num : rightSides2) {
            System.out.printf("%d -> ", num);
        }
        System.out.println("NULL");
    }
}
