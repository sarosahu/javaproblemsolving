package com.algo.ae.string;

/**
 * Caesar Cipher Encryptor
 *
 * Given a non-empty string of lowercase letters and non-negative integers
 * representing a key, write a function that returns a new string obtained
 * by shifting every letter in the input string by k positions in the
 * alphabet, where k is the key.
 *
 * Note that letters should "wrap" around the alphabet; in other words,
 * the letter z shifted by one returns the letter a.
 *
 * Sample input
 * string = "xyz"
 * key = 2
 *
 * Sample output: "zab"
 */
public class CaesarCypherEncryptor {
    public static String caesarCypherEncryptor(String str, int key) {
        char[] newLetters = new char[str.length()];
        int newKey = key % 26;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < str.length(); ++i) {
            newLetters[i] = getNewLetter(str.charAt(i), newKey, alphabet);
        }
        return new String(newLetters);
    }

    public static char getNewLetter(char letter, int key, String alphabet) {
        int newLetterCode = alphabet.indexOf(letter) + key;
        return alphabet.charAt(newLetterCode % 26);
    }

    public static String caesarCypherEncryptor2(String str, int key) {
        // Write your code here.
        if (str == null || str.isEmpty()) {
            return str;
        }
        char [] newString = new char[str.length()];
        key %= 26;
        for (int i = 0; i < str.length(); ++i) {
            int offset = str.charAt(i) + key > 'z' ? str.charAt(i) + key - 'z': str.charAt(i) + key - 'a' + 1;
            newString[i] = (char)('a' + offset - 1);
        }
        return new String(newString);
    }
}
