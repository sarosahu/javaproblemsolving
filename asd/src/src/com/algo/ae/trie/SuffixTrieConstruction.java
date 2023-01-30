package com.algo.ae.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Suffix Trie Construction
 *
 * Write a SuffixTrie class for a Suffix-Trie-like data structure. The class
 * should have a root property set to be the root node of the trie and should
 * support:
 * - Creating the trie from a string; this will be done by calling the
 *   populateSuffixTrieFrom method upon class instantiation, which should
 *   populate the root of the class.
 * - Searching for strings in the trie.
 *
 * Note: Every string added to the trie should end with the special
 * "endSymbol" character: "*".
 *
 * Sample Input (for creation)
 * string = "babc"
 *
 * Sample Output(for creation)
 * {
 *     "c": {"*": true},
 *     "b": {
 *         "c":{"*": true},
 *         "a":{"b": {"c":{"*": true}}},
 *     },
 *     "a":{"b":{"c":{"*":true}}}
 * }
 */
public class SuffixTrieConstruction {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }

        public void populateSuffixTrieFrom(String str) {
            // Write your code here.
            for (int i = 0; i < str.length(); ++i) {
                insertSubstringStartingAt(i, str);
            }
        }

        public void insertSubstringStartingAt(int idx, String str) {
            TrieNode node = root;
            for (int i = idx; i < str.length(); ++i) {
                char letter = str.charAt(i);
                if (!node.children.containsKey(letter)) {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                }
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
        }

        public boolean contains(String str) {
            // Write your code here.
            TrieNode node = root;
            for (int i = 0; i < str.length(); ++i) {
                char letter = str.charAt(i);
                if (!node.children.containsKey(letter)) {
                    return false;
                }
                node = node.children.get(letter);
            }
            return node.children.containsKey(endSymbol);
        }
    }
}
