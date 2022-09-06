package com.algo.ae.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheApplication {
    static class LRUCache {
        int maxSize;
        final DoublyLinkedList list = new DoublyLinkedList();
        final Map<String, ListNode> cache = new HashMap<>();

        public LRUCache(int maxSize) {
            this.maxSize = maxSize > 1 ? maxSize : 1;
        }

        public void insertKeyValuePair(String key, int value) {
            // Write your code here.
            if (cache.containsKey(key)) {
                ListNode node = cache.get(key);
                if (node == list.getHead()) {
                    node.data.setValue(value);
                    return;
                }
                list.remove(node);
                cache.remove(key);
            } else if (list.size == maxSize) {
                Data oldData = list.removeLast();
                cache.remove(oldData.key);
            }
            // Create a new entry at the beginning.
            ListNode node = list.addFirst(key, value);
            cache.put(key, node);
        }

        public LRUResult getValueFromKey(String key) {
            // Write your code here.
            ListNode node = cache.get(key);
            if (node == null) {
                return null;
            }
            LRUResult result = new LRUResult(true, node.data.value);
            // Make this data as the most recently used.
            if (node != list.getHead()) {
                list.remove(node);
                node = list.addFirst(key, node.data.value);
                cache.put(key, node);
            }
            return result;
        }

        public String getMostRecentKey() {
            // Write your code here.
            return list.getHead().data.key;
        }
    }

    static class Data {
        String key;
        int value;
        public Data(String key, int value) {
            this.key = key;
            this.value = value;
        }
        public void setValue(int value) {
            this.value = value;
        }
    }

    static class ListNode {
        Data data;
        ListNode next;
        ListNode prev;
        public ListNode(String key, int value) {
            this.data = new Data(key, value);
        }
    }

    static class DoublyLinkedList {
        ListNode head;
        ListNode tail;
        int size;

        public DoublyLinkedList() {
            this.size = 0;
            this.head = null;
            this.tail = null;
        }

        public ListNode getHead() {
            return this.head;
        }

        public ListNode getTail() {
            return this.tail;
        }

        public ListNode addFirst(String key, int value) {
            ListNode node = new ListNode(key, value);
            if (this.head == null) {
                this.head = node;
                this.tail = node;
            } else {
                node.next = this.head;
                this.head.prev = node;
                this.head = node;
            }
            ++size;
            return node;
        }

        public Data removeLast() {
            if (this.size == 0) {
                return null;
            }
            ListNode tailNode = this.tail;
            this.tail = tailNode.prev;
            if (this.tail == null) {
                this.head = null;
            } else {
                this.tail.next = null;
            }
            --size;

            return tailNode.data;
        }

        /**
         * Remove a node provided in the argument.
         */
        public void remove(ListNode node) {
            ListNode prev = node.prev;
            ListNode next = node.next;

            if (this.head == node) {
                if (this.size == 1) {
                    this.head = null;
                    this.tail = null;
                } else {
                    this.head = this.head.next;
                    this.head.prev = null;
                }
            } else if (this.tail == node) {
                this.tail = this.tail.prev;
                this.tail.next = null;
            } else {
                // If node is other than head and tail
                next.prev = prev;
                prev.next = next;
            }
            --size;
        }
    }

    static class LRUResult {
        boolean found;
        int value;

        public LRUResult(boolean found, int value) {
            this.found = found;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        cache.insertKeyValuePair("A", 1);
        cache.insertKeyValuePair("B", 2);
        cache.insertKeyValuePair("C", 3);
        cache.insertKeyValuePair("D", 4);
        cache.insertKeyValuePair("E", 5);

        LRUResult result = cache.getValueFromKey("C");

    }
}
