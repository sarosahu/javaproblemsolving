package com.algo.ae.trie;

import java.util.*;

/**
 * Multi String Search
 *
 * Write a function that takes in a big string and an array of
 * small strings, all of which are smaller in length than the
 * big string. The function should return an array of booleans,
 * where each booleans, where each boolean represents whether
 * the small string at that index in the array of small strings
 * is contained in the big string.
 *
 * Note that you can't use language-built-in string matching methods.
 *
 * Sample Input#1:
 * bigString: "this is a big string"
 * smallStrings : ["this", "yo", "is", "a", "bigger", "string", "kappa"]
 *
 * Sample output#1:
 * [true, false, true, true, false, true, false]
 *
 * Sample Input#2:
 * bigString = "abcdefghijklmnopqrstuvwxyz"
 * smallStrings = ["abc", "mnopqr", "wyz", "no", "e", "tuuv"]
 *
 * Sample Output#2: [true, true, false, true, true, false]
 */
public class MultiStringSearch {
    // Brute force approach. Time: O(bns), space : O(n)
    public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
        List<Boolean> results = new ArrayList<>();
        for (String small : smallStrings) {
            results.add(isInBigString(bigString, small));
        }
        return results;
    }

    public static boolean isInBigString(String big, String small) {
        for (int i = 0; i < big.length(); ++i) {
            if (i + small.length() > big.length()) {
                break;
            }

            if (isInBigString(big, small, i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInBigString(String big, String small, int startIdx) {
        int leftBigIdx = startIdx;
        int rightBigIdx = leftBigIdx + small.length() - 1;
        int leftSmallIdx = 0;
        int rightSmallIdx = small.length() - 1;

        while (leftBigIdx <= rightBigIdx) {
            if (big.charAt(leftBigIdx) != small.charAt(leftSmallIdx) ||
                    big.charAt(rightBigIdx) != small.charAt(rightSmallIdx)) {
                return false;
            }
            ++leftBigIdx;
            --rightBigIdx;
            ++leftSmallIdx;
            --rightSmallIdx;
        }
        return true;
    }

    // Suffix trie approach.
    // Add all the small strings into trie, then search the big string
    // by using trie.
    // Time: O(ns + bs) , space O(ns)
    public static List<Boolean> multiStringSearchE(String bigString, String[] smallStrings) {
        // Write your code here.
        Trie trie = new Trie();
        for (String smallString : smallStrings) {
            trie.insert(smallString);
        }
        Set<String> containedStrings = new HashSet<>();
        for (int i = 0; i < bigString.length(); ++i) {
            findSmallStringsIn(bigString, i, trie, containedStrings);
        }
        List<Boolean> solutions = new ArrayList<>();
        for (String str : smallStrings) {
            solutions.add(containedStrings.contains(str));
        }
        return solutions;
    }

    public static void findSmallStringsIn(String str,
                                          int startIndex,
                                          Trie trie,
                                          Set<String> containedStrings) {
        TrieNode currentNode = trie.root;
        for (int i = startIndex; i < str.length(); ++i) {
            char letter = str.charAt(i);
            if (!currentNode.children.containsKey(letter)) {
                break;
            }
            currentNode = currentNode.children.get(letter);
            if (currentNode.children.containsKey(trie.endSymbol)) {
                containedStrings.add(currentNode.word);
            }
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    static class Trie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public void insert(String str) {
            TrieNode node = root;
            for (int i = 0; i < str.length(); ++i) {
                char letter = str.charAt(i);
                if (!node.children.containsKey(letter)) {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                }
                node = node.children.get(letter);
            }
            node.children.put(endSymbol, null);
            node.word = str;
        }

    }
}
