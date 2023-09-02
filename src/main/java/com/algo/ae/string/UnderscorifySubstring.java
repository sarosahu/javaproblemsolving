package com.algo.ae.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Underscorify substring
 *
 * Write a function that takes in two strings: a main string and a potential substring
 * of the main string. The function should return a version of the main string with
 * every instance of the substring in it wrapped between underscores.
 *
 * If two or more instances of the substring in the main string overlap each other or
 * sit side by side, the underscores relevant to these substrings should only appear
 * on the far left of the leftmost substring and on the far right of the rightmost
 * substring. If the main string doesn't contain the other string at all, the function
 * should return the main string intact.
 *
 * Sample Input:
 *   string = "testthis is a testtest to see if testestest it works"
 *   substring = "test"
 *
 * Sample Output: "_test_this is a _testtest_ to see if _testestest_ it works"
 *
 */
public class UnderscorifySubstring {

    // Average case : O(n + m), Space : O(n) -- n is the length of the main
    // string and m is the length of the substring.
    public static String underscorifySubstring(String str, String substring) {
        List<Integer[]> locations = getLocations(str, substring);
        locations = collapse(locations);
        return underscorify(str, locations);
    }

    public static List<Integer[]> getLocations(String str, String substring) {
        List<Integer[]> locations = new ArrayList<>();
        int startIdx = 0;
        while (startIdx < str.length()) {
            int nextIdx = str.indexOf(substring, startIdx);
            if (nextIdx != -1) {
                locations.add(new Integer[] {nextIdx, nextIdx + substring.length()});
                startIdx = nextIdx + 1;
            } else {
                break;
            }
        }
        return locations;
    }

    public static List<Integer[]> collapse(List<Integer[]> locations) {
        if (locations.size() == 0) {
            return locations;
        }

        List<Integer[]> newLocations = new ArrayList<>();
        newLocations.add(locations.get(0));
        Integer[] previous = newLocations.get(0);

        for (int i = 1; i < locations.size(); ++i) {
            Integer[] current = locations.get(i);
            if (current[0] <= previous[1]) {
                previous[1] = current[1];
            } else {
                newLocations.add(current);
                previous = current;
            }
        }

        return newLocations;
    }

    public static String underscorify(String str, List<Integer[]> locations) {
        int locationsIdx = 0;
        int stringIdx = 0;
        boolean inBetweenUnderscores = false;
        List<String> finalChars = new ArrayList<>();
        int i = 0;

        while (stringIdx < str.length() && locationsIdx < locations.size()) {
            if (stringIdx == locations.get(locationsIdx)[i]) {
                finalChars.add("_");
                inBetweenUnderscores = !inBetweenUnderscores;
                if (!inBetweenUnderscores) {
                    locationsIdx++;
                }
                i = i == 1 ? 0 : 1;
            }
            finalChars.add(String.valueOf(str.charAt(stringIdx)));
            stringIdx += 1;
        }

        if (locationsIdx < locations.size()) {
            finalChars.add("_");
        } else if (stringIdx < str.length()) {
            finalChars.add(str.substring(stringIdx));
        }
        return String.join("", finalChars);
    }
}
