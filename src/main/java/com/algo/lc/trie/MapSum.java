package com.algo.lc.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Map Sum Pairs
 *
 * Design a map that allows you to do the following:
 *
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key
 * already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 *
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 * Constraints:
 *
 * 1 <= key.length, prefix.length <= 50
 * key and prefix consist of only lowercase English letters.
 * 1 <= val <= 1000
 * At most 50 calls will be made to insert and sum.
 */
public class MapSum {
    private TrieNode root = new TrieNode();
    private Map<String, Integer> map = new HashMap<>();

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);
        map.put(key, val);
        TrieNode node = root;
        node.setValue(delta);
        for (int i = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (!node.hasChild(c)) {
                node.addChild(c);
            }
            node = node.getChild(c);
            node.setValue(node.getValue() + delta);
        }
        //node.setValue(val);
    }


    public int sum(String prefix) {
        TrieNode node = root;
        int sum = 0;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (!node.hasChild(c)) {
                return 0;
            }
            node = node.getChild(c);
            //sum = i == prefix.length() - 1 ? sum : sum + node.getValue();
        }
        return node.getValue();
        /*Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            TrieNode curr = queue.poll();
            sum += curr.getValue();
            for (TrieNode next : curr.getChildren().values()) {
                queue.offer(next);
            }
        }
        return sum;*/
    }

    public static class TrieNode {
        private Map<Character, TrieNode> children = new HashMap<>();
        private int val = 0;
        public void addChild(char c) {
            children.put(c, new TrieNode());
        }
        public Map<Character, TrieNode> getChildren() {
            return this.children;
        }
        public TrieNode getChild(char c) {
            return this.children.get(c);
        }
        public boolean hasChild(char c) {
            return this.children.containsKey(c);
        }
        public void setValue(int val) {
            this.val = val;
        }
        public int getValue() {
            return this.val;
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println("sum : " + mapSum.sum("ap"));

        mapSum.insert("app", 2);
        System.out.println("sum : " + mapSum.sum("ap"));

        mapSum.insert("apple", 5);
        System.out.println("sum : " + mapSum.sum("apple"));

        mapSum.insert("apple", 1);
        System.out.println("sum : " + mapSum.sum("apple"));
    }
}
