package com.algo.lc.recursion;

import java.util.*;

/**
 * Word Squares
 *
 * Given an array of unique strings words, return all the word squares you can
 * build from words. The same word from words can be used multiple times. You can
 * return the answer in any order.
 *
 * A sequence of strings forms a valid word square if the kth row and column read
 * the same string, where 0 <= k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square
 * because each word reads the same both horizontally and vertically.
 *
 * Example 1:
 *
 * Input: words = ["area","lead","wall","lady","ball"]
 * Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not
 * matter (just the order of words in each word square matters).
 *
 * Example 2:
 *
 * Input: words = ["abat","baba","atan","atal"]
 * Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 4
 * All words[i] have the same length.
 * words[i] consists of only lowercase English letters.
 * All words[i] are unique.
 */

public class WordSquares {
    int N = 0;
    String[] words = null;
    Map<String, List<String>> prefixTable = null;

    /**
     * Complexity Analysis
     *
     * Time complexity: O(N⋅26^L), where N is the number of input words and L
     * is the length of a single word.
     *
     * It is tricky to calculate the exact number of operations in the backtracking
     * algorithm. We know that the trace of the backtrack would form a n-ary tree.
     * Therefore, the upper bound of the operations would be the total number of nodes
     * in a full-blossom n-ary tree.
     *
     * In our case, at any node of the trace, at maximum it could have 26 branches
     * (i.e. 26 alphabet letters). Therefore, the maximum number of nodes in a 26-ary
     * tree would be approximately 26^L
     * L
     *  .
     *
     * In the loop around the backtracking function, we enumerate the possibility of
     * having each word as the starting word in the word square. As a result, in total
     * the overall time complexity of the algorithm should be O(N⋅26^L).
     * As large as the time complexity might appear, in reality, the actual trace of
     * the backtracking is much smaller than its upper bound, thanks to the constraint
     * checking (symmetric of word square) which greatly prunes the trace of the backtracking.
     *
     * Space Complexity: O(N⋅L+N⋅L/2) where N is the number of words and L is the length of a single word.
     *
     * The first half of the space complexity (i.e. N⋅L) is the values in the hashtable,
     * where we store LLL times all words in the hashtable.
     *
     * The second half of the space complexity (i.e. N⋅L/2) is the space took by the keys
     * of the hashtable, which include all prefixes of all words.
     *
     * In total, we could say that the overall space of the algorithm is proportional
     * to the total words times the length of a single word.
     */
    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        this.N = words[0].length();

        List<List<String>> results = new ArrayList<>();
        this.buildPrefixTable(words);

        for (String word : words) {
            LinkedList<String> wordSquares = new LinkedList<>();
            wordSquares.addLast(word);
            this.backtracking(1, wordSquares, results);
        }
        return results;
    }

    private void backtracking(int step,
                              LinkedList<String> wordSquares,
                              List<List<String>> results)
    {
        if (step == N) {
            results.add((List<String>) wordSquares.clone());
            return;
        }
        StringBuilder prefix = new StringBuilder();
        for (String word : wordSquares) {
            prefix.append(word.charAt(step));
        }

        for (String candidate : this.getWordsWithPrefix(prefix.toString())) {
            wordSquares.addLast(candidate);
            this.backtracking(step + 1, wordSquares, results);
            wordSquares.removeLast();
        }
    }

    private void buildPrefixTable(String[] words) {
        this.prefixTable = new HashMap<String, List<String>>();
        for (String word : words) {
            for (int i = 1; i < this.N; ++i) {
                String prefix = word.substring(0, i);
                List<String> wordList = this.prefixTable.get(prefix);
                if (wordList == null) {
                    wordList =  new ArrayList<String>();
                    this.prefixTable.put(prefix, wordList);
                }
                //this.prefixTable.get(prefix).add(word);
                wordList.add(word);
            }
        }
    }

    private List<String> getWordsWithPrefix(String prefix) {
        List<String> wordList = this.prefixTable.get(prefix);
        return (wordList != null) ? wordList : new ArrayList<>();
    }

    public static void main(String[] args) {
        WordSquares obj = new WordSquares();
        String[] words = {"area","lead","wall","lady","ball"};
        List<List<String>> lists = obj.wordSquares(words);
        for (List<String> l : lists) {
            for (String w : l) {
                System.out.printf("%s ", w);
            }
            System.out.println();
        }
    }
}
