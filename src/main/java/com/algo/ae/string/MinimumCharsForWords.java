package com.algo.ae.string;

import java.util.*;

/**
 * Minimum Characters For Words
 *
 * Write a function that takes in an array of words and returns the smallest array
 * of characters needed to form all of the words. The characters don't need to be
 * in any particular order.
 * For example, the characters ["y", "r", "o", "u"] are needed to form the words
 * ["your", "you", "or", "yo"].
 *
 * Note: the input words won't contain any spaces; however, they might contain
 * punctuation and/or special characters.
 *
 * Sample input:
 * words = ["this", "that", "did", "deed", "them!", "a"]
 *
 * Sample output:
 * ["t", "t", "h", "i", "s", "a", "d", "d", "e", "e", "m", "!"]
 */
public class MinimumCharsForWords {

    public static char[] minimumCharactersForWords(String[] words) {
        Map<Character, CharFrequencies> charFreq = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!charFreq.containsKey(c)) {
                    charFreq.put(c, new CharFrequencies(1, 1));
                } else {
                    CharFrequencies charFreqObj = charFreq.get(c);
                    int currCharCount = charFreqObj.getCurrCount();
                    if (currCharCount + 1 > charFreqObj.getMaxCount()) {
                        charFreqObj.setMaxCount(currCharCount + 1);
                    }
                    charFreqObj.setCurrCount(currCharCount + 1);
                    charFreq.put(c, charFreqObj);
                }
            }
            for (char c : word.toCharArray()) {
                charFreq.get(c).setCurrCount(0);
            }
        }

        List<Character> chars = new ArrayList<>();
        for (Map.Entry<Character, CharFrequencies> entry : charFreq.entrySet()) {
            int maxCount = entry.getValue().getMaxCount();
            for (int i = 0; i < maxCount; ++i) {
                chars.add(entry.getKey());
            }
        }

        char[] charsArr = new char[chars.size()];
        for (int i = 0; i < chars.size(); ++i) {
            charsArr[i] = chars.get(i);
        }

        return charsArr;
    }

    public static char[] minimumCharactersForWordsE(String[] words) {
        Map<Character, CharFrequencies> charFreq = new HashMap<>();

        for (String word : words) {
            Set<CharFrequencies> CharFreqHashTable = new HashSet<>();

            for (char c : word.toCharArray()) {
                if (!charFreq.containsKey(c)) {
                    charFreq.put(c, new CharFrequencies(1, 1));
                } else {
                    int currCharCount = charFreq.get(c).getCurrCount();
                    if (currCharCount + 1 > charFreq.get(c).getMaxCount()) {
                        charFreq.get(c).setMaxCount(currCharCount + 1);
                    }
                    charFreq.get(c).setCurrCount(currCharCount + 1);
                }
                if (!CharFreqHashTable.contains(charFreq.get(c))) {
                    CharFreqHashTable.add(charFreq.get(c));
                }
            }

            for (CharFrequencies charFreqObj : CharFreqHashTable) {
                charFreqObj.setCurrCount(0);
            }
        }

        List<Character> chars = new ArrayList<>();
        for (Map.Entry<Character, CharFrequencies> entry : charFreq.entrySet()) {
            int maxCount = entry.getValue().getMaxCount();
            for (int i = 0; i < maxCount; ++i) {
                chars.add(entry.getKey());
            }
        }

        char[] charsArr = new char[chars.size()];
        for (int i = 0; i < chars.size(); ++i) {
            charsArr[i] = chars.get(i);
        }

        return charsArr;
    }

    static class CharFrequencies {
        int currCount;
        int maxCount;
        public CharFrequencies(int currCount, int maxCount) {
            this.currCount = currCount;
            this.maxCount = maxCount;
        }

        public void setCurrCount(int currCount) {
            this.currCount = currCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }

        public int getCurrCount() {
            return this.currCount;
        }

        public int getMaxCount() {
            return this.maxCount;
        }
    }

    public static void main(String[] args) {
        String[] words = {"this", "that", "did", "deed", "them!", "a"};
        char[] charList = minimumCharactersForWordsE(words);
        System.out.println("The minimum character list : ");
        for (char c : charList) {
            System.out.println(c);
        }
    }
}
