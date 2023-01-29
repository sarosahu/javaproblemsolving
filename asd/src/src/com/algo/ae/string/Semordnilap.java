package com.algo.ae.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that takes in a list of unique strings and returns
 * a list of semordnilap pairs.
 *
 * A semordnilap pair is defined as a set of different strings where the
 * reverse of one word is hte same as the forward version of the other.
 * For example the words "diaper" and "repaid" are a semordnilap pair,
 * as are the words "palindromes" and "semordnilap".
 *
 * The order of the returned pairs and the order of the strings within
 * each pair doesn't matter.
 *
 * Sample Input:
 * words = ["diaper", "abc", "test", "cba", "repaid"]
 *
 * Sample Output:
 * [["diaper", "repaid"], ["abc", "cba"]]
 */
public class Semordnilap {

    // Time: O(n * m) , space : O(n * m)
    // n is number of words and m is the length of the longest word.
    public ArrayList<ArrayList<String>> semordnilap(String[] words) {
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }

        ArrayList<ArrayList<String>> palindromesList = new ArrayList<>();
        for (String word : words) {
            set.remove(word);
            //String palindromeWord = getPalindrome(word);
            String palindromeWord = new StringBuilder(word).reverse().toString();
            if (set.contains(palindromeWord)) {
                ArrayList<String> palindromes = new ArrayList<>();
                palindromes.add(word);
                palindromes.add(palindromeWord);
                set.remove(palindromeWord);
                palindromesList.add(palindromes);
            }
        }
        return palindromesList;
    }

    public String getPalindrome(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; --i) {
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }
}
