package com.algo.lc.other;

import java.util.*;

/**
 *
 */
public class SerializeDeserializeNaryTree {
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    static class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            StringBuilder builder = new StringBuilder();
            serialize(root, builder);
            return builder.toString();
        }
        private void serialize( Node root, StringBuilder builder) {
            if( root == null) {
                builder.append("#,");
                return;
            }
            builder.append(root.val).append(",");
            for( Node child : root.children) {
                serialize(child, builder);
            }
            builder.append("$,");
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            System.out.println(data);
            Deque<String> queue = new ArrayDeque<>( Arrays.asList(data.split(",")));
            return deserialize(queue);
        }
        private Node deserialize(Deque<String> queue ) {
            if( queue.isEmpty()) {
                return null;
            }
            String peek = queue.poll();
            if( peek.equals("$") || peek.equals("#")) {
                return null;
            }

            int value = Integer.parseInt(peek);

            List<Node> child = new ArrayList<>();

            while( !queue.isEmpty() && !queue.peek().equals("$")) {
                child.add( deserialize(queue));
            }
            Node root = new Node(value, child);
            queue.poll();
            return root;
        }
    }
}
