package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Generate Div Tags
 *
 * Write a function that takes in a positive integer "numberOfTags" and
 * returns a list of all the valid strings that you can generate with
 * that number of matched <div></div> tags.
 *
 * A string is valid and contains matched <div></div> tags if for every
 * opening tag <div>, there's a closing tag </tag> that comes after the
 * opening tag and that is not used as a closing tag for another opening
 * tag. Each output string should contain exactly "numberOfTags" opening
 * tags and "numberOfTags" closing tags.
 *
 * For example, given numberOfTags = 2, the valid strings to return would
 * be:
 * <div></div><div></div>
 * <div><div></div></div>
 *
 * Note that the output strings don't need to be in any particular order.
 *
 * Sample input : numberOfTags = 3
 *
 * Sample output :
 * [
 * "<div><div><div></div></div></div>",
 * "<div><div></div><div></div></div>",
 * "<div><div></div></div><div></div>",
 * "<div></div><div><div></div></div>",
 * "<div></div><div></div><div></div>",
 * ]
 *
 * This is same as another question on generating all valid (i.e.
 * properly opened and closed) combinations of n pairs of parentheses.
 * Example:
 * input 3
 * output: ((())), (()()), (())(), ()(()), ()()()
 */
public class GenerateDivTags {
    public static String BEGIN_TAG = "<div>";
    public static String END_TAG = "</div>";
    // Brute force approach
    public static ArrayList<String> generateDivTags(int numberOfTags) {
        Set<String> validTags = generateDivTagsHelper(numberOfTags);
        ArrayList<String> list = new ArrayList<>();
        for (String str : validTags) {
            list.add(str);
        }
        return list;
    }

    public static Set<String> generateDivTagsHelper(int remaining) {
        Set<String> set = new HashSet<>();
        if (remaining == 0) {
            set.add("");
        } else {
            Set<String> prev = generateDivTagsHelper(remaining - 1);
            for (String str : prev) {
                for (int i = 0; i < str.length();) {
                    if (str.charAt(i) == '<' && str.charAt(i + 1) == 'd') {
                        int lastBeginTagIdx = i + BEGIN_TAG.length() - 1;
                        String s = insertDivTagInside(str, lastBeginTagIdx);
                        set.add(s);
                        //i += lastBeginTagIdx;
                    }
                    ++i;
                }
                set.add(BEGIN_TAG + END_TAG + str);
            }
        }
        return set;
    }

    public static String insertDivTagInside(String str, int lastIdx) {
        String left = str.substring(0, lastIdx + 1);
        String right = str.substring(lastIdx + 1, str.length());
        return left + BEGIN_TAG +END_TAG + right;
    }

    public static ArrayList<String> generateDivTagsE(int numberOfTags) {
        ArrayList<String> validTags = new ArrayList<>();

        generateDivTagsFromPrefix(numberOfTags, numberOfTags, "", validTags);

        return validTags;
    }

    public static void generateDivTagsFromPrefix(int beginTagsRemaining,
                                          int endTagsRemaining,
                                          String prefix,
                                          ArrayList<String> result)
    {
        if (beginTagsRemaining < 0 || endTagsRemaining < beginTagsRemaining) {
            return;
        }

        if (beginTagsRemaining == 0 && endTagsRemaining == 0) {
            result.add(prefix);
        } else {
            if (beginTagsRemaining > 0) {
                String newPrefix = prefix + "<div>";
                generateDivTagsFromPrefix(beginTagsRemaining - 1, endTagsRemaining, newPrefix, result);
            }

            if (beginTagsRemaining < endTagsRemaining) {
                String newPrefix = prefix + "</div>";
                generateDivTagsFromPrefix(beginTagsRemaining, endTagsRemaining - 1, newPrefix, result);
            }
        }
    }
    public static void main(String[] args) {
        ArrayList<String> list = generateDivTags(4);
        System.out.println("The tags representations:");
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println("--------------------------------------");

        ArrayList<String> list2 = generateDivTagsE(4);
        System.out.println("The tags representations:");
        for (String str : list2) {
            System.out.println(str);
        }
    }
}
