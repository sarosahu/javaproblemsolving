package com.algo.lc.recursion.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 */
public class LetterCombinationsInPhoneNumber {
    static Map<Character, String> numToChars = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
    );
    /**
     * Complexity Analysis

     Time complexity:O(4^N*N), where N is the length of digits. Note that 4 in this expression
     is referring to the maximum value length in the hash map, and not to the length of the input.

     The worst-case is where the input consists of only 7s and 9s. In that case, we have to
     explore 4 additional paths for every extra digit. Then, for each combination, it costs
     up to N to build the combination. This problem can be generalized to a scenario where
     numbers correspond with up to M digits, in which case the time complexity would be
     O(M^N⋅N). For the problem constraints, we're given, M=4, because of digits 7 and 9
     having 4 letters each.

     Space complexity: O(N), where N is the length of digits.

     Not counting space used for the output, the extra space we use relative to input size is the
     space occupied by the recursion call stack. It will only go as deep as the number of digits in
     the input since whenever we reach that depth, we backtrack.

     As the hash map does not grow as the inputs grows, it occupies O(1) space.
     */

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        StringBuilder combination = new StringBuilder();
        helper(digits, result, combination, 0);

        return result;
    }

    private void helper(String digits,
                        List<String> result,
                        StringBuilder combination,
                        int index) {
        if (index == digits.length()) {
            result.add(combination.toString());
            return;
        }
        char digit = digits.charAt(index);
        String value = numToChars.get(digit);
        for (char c : value.toCharArray()) {
            combination.append(c);
            helper(digits, result, combination, index + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }
}
