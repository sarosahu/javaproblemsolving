package com.algo.lc.treegraphs;

import java.util.*;

/**
 * Word Ladder
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary
 * wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be
 * in wordList.
 * sk == endWord
 *
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number
 * of words in the shortest transformation sequence from beginWord to endWord, or 0 if
 * no such sequence exists.
 *
 * Example 1:
 * ----------
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog",
 * which is 5 words long.
 *
 * Example 2:
 * ----------
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */

public class WordLadder {
    private int L;
    private Map<String, List<String>> allComboDict = new HashMap<>();
    static class LevelInfo {
        String word;
        Integer level;
        public LevelInfo(String word, Integer level) {
            this.word = word;
            this.level = level;
        }
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Since all the words are of same length
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < L; ++i) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        }

        // Queue for BFS
        Queue<LevelInfo> queue = new LinkedList<>();
        queue.add(new LevelInfo(beginWord, 1));

        // Visited to make sure we don't repeat processing same word
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            LevelInfo node = queue.poll();
            String word = node.word;
            int level = node.level;

            for (int i = 0; i < L; ++i) {
                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>()))
                {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise add it to the BFS queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        queue.add(new LevelInfo(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length
        this.L = beginWord.length();

        for (String word : wordList) {
            for (int i = 0; i < L; ++i) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> transformations =
                        allComboDict.getOrDefault(newWord, new ArrayList<>());
                allComboDict.put(newWord, transformations);
                allComboDict.get(newWord).add(word);
            }
        }

        // Queue for bidirectional BFS
        // BFS starting with beginWord
        Queue<LevelInfo> queue1 = new LinkedList<>();
        Queue<LevelInfo> queue2 = new LinkedList<>();
        queue1.add(new LevelInfo(beginWord, 1));
        queue2.add(new LevelInfo(endWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);
        int ans = -1;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            // Progress forward one step from the shorten queue
            if (queue1.size() <= queue2.size()) {
                ans = visitWordNode(queue1, visitedBegin, visitedEnd);
            } else {
                ans = visitWordNode(queue2, visitedEnd, visitedBegin);
            }

            if (ans > -1) {
                return ans;
            }
        }
        return 0;
    }

    private int visitWordNode(Queue<LevelInfo> queue,
                              Map<String, Integer> visited1,
                              Map<String, Integer> visited2)
    {
        while (!queue.isEmpty()) {
            LevelInfo node = queue.poll();
            String word = node.word;
            int level = node.level;

            for (int i = 0; i < L; ++i) {
                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (visited2.containsKey(adjacentWord)) {
                        return level + visited2.get(adjacentWord);
                    }
                    if (!visited1.containsKey(adjacentWord)) {
                        // Save the level as the value of the dictionary, to save no of hops.
                        visited1.put(adjacentWord, level + 1);
                        queue.add(new LevelInfo(adjacentWord, level + 1));
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        int ladderLen = ladderLength("hit", "cog", wordList);
        System.out.println("Ladder length : " + ladderLen);
        WordLadder wordLadder = new WordLadder();
        ladderLen = wordLadder.ladderLength2("hit", "cog", wordList);
        System.out.println("Ladder length 2 : " + ladderLen);
    }
}
