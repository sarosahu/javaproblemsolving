package com.algo.ae.recursion;

public class InterweavingStrings {
    public static boolean interweavingStrings(String one, String two, String three) {
        // Write your code here.
        if (three.length() != one.length() + two.length()) {
            return false;
        }
        int iter1 = 0, iter2 = 0, iter3 = 0;
        while (iter1 < one.length() && iter2 < two.length()) {
            char c = three.charAt(iter3);
            if (c == one.charAt(iter1)) {
                ++iter1;
            } else if (c == two.charAt(iter2)) {
                ++iter2;
            } else {
                return false;
            }
            ++iter3;
        }
        return true;
    }

    public static void main(String[] args) {
        String one = "algoexpert";
        String two = "your-dream-job";
        String three = "your-algodream-expertjob";

        boolean flag = interweavingStrings(one, two, three);
        if (flag) {
            System.out.printf("%s is interweaving of %s and %s ", three, one, two);
        } else {
            System.out.printf("%s is not interweaving of %s and %s ", three, one, two);
        }
    }
}
