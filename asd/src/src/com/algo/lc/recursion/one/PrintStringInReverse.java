package com.algo.lc.recursion.one;

public class PrintStringInReverse {
    public void printReverse(String s) {
        if (s != null) {
            printReverseHelper(0, s);
        }
    }

    public void printReverseHelper(int idx, String s) {
        if (idx == s.length()) {
            return;
        }
        printReverseHelper(idx + 1, s);
        System.out.print(s.charAt(idx));
    }

    public static void main(String[] args) {
        PrintStringInReverse obj = new PrintStringInReverse();
        obj.printReverse("saroj");
        obj.printReverse("");
        obj.printReverse(null);
    }
}
