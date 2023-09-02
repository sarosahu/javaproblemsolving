package com.algo.ae.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Generate Document
 *
 * You're given a string of available characters and a string representing
 * a document that you need to generate. Write a function that determines
 * if you can generate the document using the available characters. If
 * you can generate the document, hyour function should return true;
 * otherwise, it should return  false.
 *
 * You're only able to generate the document if the frequency of unique
 * characters in the characters string is greater than or equal to the
 * frequency of unique characters in the document string. For example,
 * if you're given characters = "abcabc" and document = "aabbccc", you
 * can't generate the document because you're missing one c.
 *
 * The document that you need to create may contain any characters,
 * including special characters, capital letters, numbers and spaces.
 *
 * Note: You can always generate the empty string ("").
 *
 * Sample Input:
 * characters = "Bste!hetsi ogEAxpelrt x "
 * document = "AlgoExpert is the Best!"
 *
 * Sample output: true
 */
public class GenerateDocument {
    public boolean generateDocument(String characters, String document) {
        if (document.length() > characters.length()) {
            return false;
        }
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < characters.length(); ++i) {
            char c = characters.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < document.length(); ++i) {
            char c = document.charAt(i);
            if (!freq.containsKey(c)) {
                return false;
            } else {
                int count = freq.get(c);
                count -= 1;
                if (count == 0) {
                    freq.remove(c);
                }
            }
        }
        return true;
    }
}
