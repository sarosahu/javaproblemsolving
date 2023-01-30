package com.algo.ae.string;

import java.util.ArrayList;

/**
 * Valid IP Addresses
 * You're given a string of length 12 or smaller, containing only digits.
 * Write a function that returns all the possible IP addresses that can
 * be created by inserting three dot (.) in the string.
 *
 * An IP address is a sequence of 4 +ve integers that are separated by
 * dot(.)s, where each individual integer is within the range 0 - 255,
 * inclusive.
 *
 * An IP address isn't valid if any of the individual integers contains
 * leading 0 s. For example, "192.168.0.1" is a valid IP address, but
 * "192.168.00.1" and "192.168.0.01" aren't, because they contain
 * "00" and "01" respectively. Another example of a valid IP address
 * is "99.1.1.10"; conversely "991.1.1.0" isn't valid, because "991"
 * is > 255.
 *
 * Your function should return IP addresses in string format and in no
 * particular order. If no valid IP addresses can be created from the
 * string, your function should return an empty list.
 *
 * Sample Input: string = "1921680"
 *
 * Sample output:
 * ["1.9.216.80",
 * "1.92.16.80",
 * "1.92.168.0",
 * "19.2.16.80",
 * "19.2.168.0",
 * "19.21.6.80",
 * "19.21.68.0",
 * "19.216.8.0",
 * "192.1.6.80",
 * "192.1.68.0",
 * "192.16.8.0"]
 */
public class ValidIPAddresses {
    public ArrayList<String> validIPAddresses(String str) {
        ArrayList<String> ipAddresses = new ArrayList<>();

        for (int i = 1; i < 4 && i < str.length(); ++i) {
            //String[] currAddressParts = new String[] {"", "", "", ""};

            String first = str.substring(0, i);
            if (!isValidPart(first)) {
                continue;
            }

            for (int j = 1; i + j < str.length() && j < 4; ++j) {
                String second = str.substring(i, i + j);
                if (!isValidPart(second)) {
                    continue;
                }

                for (int k = 1; i + j + k < str.length() && k < 4; ++k) {
                    String third = str.substring(i + j, i + j + k);
                    String fourth = str.substring(i + j + k);

                    if (isValidPart(third) && isValidPart(fourth)) {
                        String address = first + "." + second + "." + third + "." + fourth;
                        ipAddresses.add(address);
                    }
                }
            }
        }
        return ipAddresses;
    }

    public boolean isValidPart(String str) {
        if (str.length() > 3) {
            return false;
        }
        if (str.charAt(0) == '0' && str.length() > 1) {
            return false;
        }
        int stringAsInt = Integer.parseInt(str);
        if (stringAsInt > 255) {
            return false;
        }
        return true;
    }
}
