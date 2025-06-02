package com.algo.ctci.arraystring;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you can't use additional data structures ?
 * What if there are only a-z and A-Z characters, and you are allowed to use
 * any data structure and how can you optimize space ?
 *
 * Note: Assume there is no unicode character and no extended ascii characters (
 * character set from 128 till 255)
 */
public class HasUniqueCharacters {
    /**
     * Time: O(N), space: O(1), because loop will never iterate over 128
     */
    public static boolean isUnique(String str) {
        if (str.length() > 128) {
            return false;
        }
        boolean [] char_set = new boolean[128];
        for (char c : str.toCharArray()) {
            int val = c;
            System.out.println("Char[" + c + "]: " + val);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    /**
     * If the input string has only characters a-z, A-Z (also character with value
     * from 65 to 127.
     * The decimal set:
     *        0 nul    1 soh    2 stx    3 etx    4 eot    5 enq    6 ack    7 bel
     *        8 bs     9 ht    10 nl    11 vt    12 np    13 cr    14 so    15 si
     *       16 dle   17 dc1   18 dc2   19 dc3   20 dc4   21 nak   22 syn   23 etb
     *       24 can   25 em    26 sub   27 esc   28 fs    29 gs    30 rs    31 us
     *       32 sp    33  !    34  "    35  #    36  $    37  %    38  &    39  '
     *       40  (    41  )    42  *    43  +    44  ,    45  -    46  .    47  /
     *       48  0    49  1    50  2    51  3    52  4    53  5    54  6    55  7
     *       56  8    57  9    58  :    59  ;    60  <    61  =    62  >    63  ?
     *       64  @    65  A    66  B    67  C    68  D    69  E    70  F    71  G
     *       72  H    73  I    74  J    75  K    76  L    77  M    78  N    79  O
     *       80  P    81  Q    82  R    83  S    84  T    85  U    86  V    87  W
     *       88  X    89  Y    90  Z    91  [    92  \    93  ]    94  ^    95  _
     *       96  `    97  a    98  b    99  c   100  d   101  e   102  f   103  g
     *      104  h   105  i   106  j   107  k   108  l   109  m   110  n   111  o
     *      112  p   113  q   114  r   115  s   116  t   117  u   118  v   119  w
     *      120  x   121  y   122  z   123  {   124  |   125  }   126  ~   127 del
     */
    public static boolean hasUniqueLetters(String str) {
        long checker = 0;
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if ((checker & (1L << (c - 'a'))) > 0) {
                return false;
            }
            checker |= 1L << (c - 'a');
        }
        return true;
    }

    public static void main(String[] args) {
        String [] strs = {
                "Aabcdefghijkl~",
                "AabcdefghIJKlmnoPQrstUVWxyZB",
                "AabcdefghIJKlmnoPQrstUVWxyZBq",
                "AabcdefghIJKlmnoPQrstUVWxyZBg",
        };

        for (String str : strs) {
            boolean hasUnique1 = isUnique(str);
            if (hasUnique1) {
                System.out.println(str + " has unique characters");
            } else {
                System.out.println(str + " has no unique characters");
            }
            hasUnique1 = hasUniqueLetters(str);
            if (hasUnique1) {
                System.out.println(str + " has unique characters");
            } else {
                System.out.println(str + " has no unique characters");
            }
            System.out.println();
        }
    }
}
