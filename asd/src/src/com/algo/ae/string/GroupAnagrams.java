package com.algo.ae.string;

import java.util.*;

/**
 * Group Anagrams
 *
 * Write a function that takes in an array of strings and groups anagrams together.
 * Anagrams are strings made up of exactly the same letters, where order
 * doesn't matter. For example, "cinema" and "iceman" are anagrams; similarly
 * "foo" and "ofo" are anagrams.
 *
 * Your function should return a list of anagrams groups in no particular order.
 *
 * Sample input:
 * words = ["yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp"]
 *
 * Sample Output:
 * [["yo", "oy"], ["flop", "olfp"], ["act", "tac", "cat"], ["foo"]
 * ]
 */
public class GroupAnagrams {
    // TIme : O(W * N * log(N), Space: O(WN)
    // W is the number of words, N is the length of longest word.
    public static List<List<String>> groupAnagrams(List<String> words) {
        // Write your code here.
        List<List<String>> results = new ArrayList<>();
        Map<String, List<String>> hm = new HashMap<>();
        for (String word : words) {
            char[] wordArray = word.toCharArray();
            Arrays.sort(wordArray);
            String sortedWord = new String(wordArray);
            List<String> anagrams = hm.getOrDefault(sortedWord, new ArrayList<>());
            anagrams.add(word);
            hm.put(sortedWord, anagrams);
        }

        for (Map.Entry<String, List<String>> entry : hm.entrySet()) {
            results.add(entry.getValue());
        }
        return results;
    }
}
