package com.algo.ae.string;

import java.util.ArrayList;

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
