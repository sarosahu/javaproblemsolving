package com.algo.ae.recursion;

import java.util.ArrayList;

/**
 * Phone Number Mnemonics
 *
 * If you open the keypad of your mobile phone, it'll likely look like this:
 *
 * -------------------
 * |     |     |     |
 * |  1  |  2  |  3  |
 * |     | abc | def |
 * -------------------
 * |     |     |     |
 * |  4  |  5  |  6  |
 * | ghi | jkl | mno |
 * -------------------
 * |     |     |     |
 * |  7  |  8  |  9  |
 * | pqrs| tuv | wxyz|
 * -------------------
 *       |     |
 *       |  0  |
 *       |     |
 *       ------
 *
 * Almost every digit is associated with some letters in the alphabet; this allows certain
 * phone numbers to spell out actual words. For example, the phone number 8464747328 can
 * be written as timisgreat; similarly, the phone number 2686463 can be written as antoine or
 * as ant6463.
 *
 * It's important to note that a phone number doesn't represent a single sequence of letters,
 * but rather multiple combinations of letters. For instance, the digit 2 can represent
 * three different letters (a, b and c).
 *
 * A mnemonic is defined as a pattern of letters, ideas, or associations that assist in
 * remembering something. Companies oftentimes use a mnemonic for their phone number to
 * make it easier to remember.
 *
 * Given a stringified phone number of any non-zero length, write a function that returns
 * all mnemonics for this phone number, in any order.
 *
 * For this problem, a valid mnemonic may only contain letters and the digits 0 and 1. In
 * other words, if a digit is able to be represented by a letter, then it must be. Digits
 * 0 and 1 are the only two digits that don't have letter representation on the keypad.
 *
 * Note that you should rely on the keypad illustrated above for digit-letter associations.
 *
 * Sample Input:
 * phoneNumber = "1905"
 *
 * Sample Output:
 * [
 *      "1w0j",
 *      "1w0k",
 *      "1w0l",
 *      "1x0j",
 *      "1x0k",
 *      "1x0l"
 *      "1y0k",
 *      "1y0l",
 *      "1z0j",
 *      "1z0k",
 *      "1z0l",
 * ]
 */
public class PhoneNumberMnemonics {
    int kNumTelDigit = 10;
    String[] M = {
            "0",
            "1",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public ArrayList<String> phoneNumberMnemonics(String phoneNumber) {
        ArrayList<String> mnemonics = new ArrayList<>();
        StringBuilder partialMnemonics = new StringBuilder();
        phoneMnemonicsHelper(phoneNumber, 0, partialMnemonics, mnemonics);
        return mnemonics;
    }

    public void phoneMnemonicsHelper(String phoneNumber,
                                     int digit,
                                     StringBuilder partialMnemonics,
                                     ArrayList<String> mnemonics) {
        if (digit == phoneNumber.length()) {
            mnemonics.add(new String(partialMnemonics.toString()));
        } else {
            for (char c : M[phoneNumber.charAt(digit) - '0'].toCharArray()) {
                partialMnemonics.append(c);
                phoneMnemonicsHelper(phoneNumber, digit + 1, partialMnemonics, mnemonics);
                partialMnemonics.deleteCharAt(partialMnemonics.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String phoneNum = "1905";
        PhoneNumberMnemonics phoneNumMnemonics = new PhoneNumberMnemonics();
        ArrayList<String> mnemonics = phoneNumMnemonics.phoneNumberMnemonics(phoneNum);
        for (String mnemonic : mnemonics) {
            System.out.println(mnemonic);
        }
    }
}
