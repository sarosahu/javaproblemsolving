package com.algo.ae.dp;

import java.util.*;

/**
 * https://www.algoexpert.io/questions/longest-string-chain
 *
 * Longest string chain
 * Given a list of strings, write a function that returns the longest string
 * chains that can be built  from these strings.
 *
 * A string chain is defined as follows: let string A be a string in the initial
 * array; if removing any single character from string A yields a new string B
 * that's contained in the initial array of strings, then strings A and B form
 * a string chain of length 2. Similarly,if removing any single character from
 * string B yields a new string C that's contained in the initial array of strings,
 * then strings A, B, and C form a string chain of length 3.
 *
 * The function should return the string chain in descending order (i.e. from
 * the longest string to the shortest one). Note: string chain of length 1 don't
 * exist; if the list of strings doesn't contain any string chain formed by 2
 * or more strings, the function should return an empty array.
 *
 * You can assume that there will only be one longest string chain.
 *
 * Sample input: ["abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"]
 *
 * Sample output: ["abcdef", "abcde", "abde", "ade", "ae"]
 */
public class LongestStringChains {
    public static class StringChain {
        String nextString;
        Integer maxChainLength;

        public StringChain(String nextString, Integer maxChainLength) {
            this.nextString = nextString;
            this.maxChainLength = maxChainLength;
        }
    }

    public static List<String> longestStringChain(List<String> strings) {
        Map<String, StringChain> stringChains = new HashMap<>();
        for (String str : strings) {
            stringChains.put(str, new StringChain("", 1));
        }

        // Sort the strings based on their length
        List<String> sortedStrings = new ArrayList<>(strings);
        sortedStrings.sort((a, b) -> a.length() - b.length());

        for (String str : sortedStrings) {
            findLongestStringChain(str, stringChains);
        }

        return buildLongestStringChain(strings, stringChains);
    }

    public static void findLongestStringChain(String str,
                                              Map<String, StringChain> stringChains) {
        for (int i = 0; i < str.length(); ++i) {
            String smallerString = str.substring(0, i) + str.substring(i + 1);
            if (!stringChains.containsKey(smallerString)) {
                continue;
            }
            int smallerStringChainLength = stringChains.get(smallerString).maxChainLength;
            int currStringChainLength = stringChains.get(str).maxChainLength;

            if (smallerStringChainLength + 1 > currStringChainLength) {
                stringChains.get(str).maxChainLength = smallerStringChainLength + 1;
                stringChains.get(str).nextString = smallerString;
            }
        }
    }

    public static List<String> buildLongestStringChain(List<String> strings,
                                                       Map<String, StringChain> stringChains) {
        // Find the string that starts with the longest string chain
        int maxChainLength = 0;
        String chainStartingString = "";
        for (String str : strings) {
            if (stringChains.get(str).maxChainLength > maxChainLength) {
                maxChainLength = stringChains.get(str).maxChainLength;
                chainStartingString = str;
            }
        }

        List<String> longestStringChains = new ArrayList<>();
        String currString = chainStartingString;
        while (currString != "") {
            longestStringChains.add(currString);
            currString = stringChains.get(currString).nextString;
        }

        return longestStringChains.size() == 1 ? new ArrayList<String>() :
                longestStringChains;
    }

    public static void main(String[] args) {
        // Sample input: ["abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"]
        // Sample output: ["abcdef", "abcde", "abde", "ade", "ae"]
        String[] inputArray = {"abde", "abc", "abd", "abcde", "ade", "ae", "1abde", "abcdef"};
        List<String> outputs = longestStringChain(Arrays.asList(inputArray));
        System.out.println("Longest chain : ");
        for (String str : outputs) {
            System.out.printf("%s, ", str);
        }
        System.out.println();
    }
}
