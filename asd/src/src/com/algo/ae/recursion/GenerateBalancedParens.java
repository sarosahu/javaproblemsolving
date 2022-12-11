package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GenerateBalancedParens {
    public static String BEGIN = "(";
    public static String END = ")";
    public static ArrayList<String> generateBalancedParens(int numberOfTags) {
        Set<String> validTags = generateBalancedParensHelper(numberOfTags);
        ArrayList<String> list = new ArrayList<>();
        for (String str : validTags) {
            list.add(str);
        }
        return list;
    }

    public static Set<String> generateBalancedParensHelper(int remaining) {
        Set<String> set = new HashSet<>();
        if (remaining == 0) {
            set.add("");
        } else {
            Set<String> prev = generateBalancedParensHelper(remaining - 1);
            for (String str : prev) {
                for (int i = 0; i < str.length(); ++i) {
                    if (str.charAt(i) == BEGIN.charAt(0)) {
                        String s = insertDivTagInside(str, i);
                        set.add(s);
                    }
                }
                set.add(BEGIN + END + str);
            }
        }
        return set;
    }

    public static String insertDivTagInside(String str, int lastIdx) {
        String left = str.substring(0, lastIdx + 1);
        String right = str.substring(lastIdx + 1);
        return left + BEGIN +END + right;
    }

    public static void main(String[] args) {
        ArrayList<String> list = generateBalancedParens(4);
        System.out.println("The tags representations:");
        for (String str : list) {
            System.out.println(str);
        }
    }
}
