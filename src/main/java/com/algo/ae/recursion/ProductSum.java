package com.algo.ae.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Product Sum
 *
 * Write a function that takes in a "Special" array and returns its product sum.
 *
 * A "special" array is non-empty array that contains either integers or other
 * "special" arrays. The product sum of a "special" array is the sum of its
 * elements, where "special" arrays inside it are summed themselves and then
 * multiplied by their level of depth.
 *
 * The depth of a "special" array is how far nested it is. For instance, the
 * depth of [] is 1; the depth of the inner array in [[]] is 2; the depth of
 * the innermost array in [[[]]] is 3.
 *
 * Therefore, the product sum of [x, y] is x + y; the product sum of [x, [y, z]]
 * is x + 2 * (y + z); the product sum of [x, [y, [z]]] is x + 2 *(y +3z).
 *
 * Sample input:
 * array = [5, 2, [7, -1], 3, [6, [-13, 8], 4]]
 *
 * Sample Output:
 * 12
 *
 * Calculated as: 5 + 2 + 2 * (7 - 1) + 3 + 2 * (6 + 3 * (-13 +8) + 4)
 */
public class ProductSum {
    public static int productSum(List<Object> array) {
        return productSumHelper(array, 1);
    }

    public static int productSumHelper(List<Object> list, int depth) {
        int sum = 0;
        for (Object obj : list) {
            if (obj instanceof ArrayList) {
                @SuppressWarnings("unchecked")
                ArrayList<Object> listObj = (ArrayList<Object>) obj;
                int prodSumOfThisArray = productSumHelper(listObj, depth + 1);
                sum += prodSumOfThisArray;
            } else {
                sum += (int)obj;
            }
        }
        return depth * sum;
    }

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Object> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(2);
        list.add(list1);
        int productSum = productSum(list);
        System.out.printf("Product sum : " + productSum);
    }
}
