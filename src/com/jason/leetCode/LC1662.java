package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/11/1 9:21:37
 * @description
 */
public class LC1662 {
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int idx1 = 0, idx2 = 0, seek1 = 0, seek2 = 0;
        while (idx1 < word1.length && idx2 < word2.length) {
            if (word1[idx1].charAt(seek1) == word2[idx2].charAt(seek2)) {
                seek1++;
                seek2++;
            } else {
                return false;
            }
            if (seek1 == word1[idx1].length()) {
                seek1 = 0;
                idx1++;
            }
            if (seek2 == word2[idx2].length()) {
                seek2 = 0;
                idx2++;
            }
        }
        return idx1 == word1.length && idx2 == word2.length;
    }

    public static void main(String[] args) {
        String[] word1 = {"ab","c"};
        String[] word2 = {"a","bc"};
        System.out.println(arrayStringsAreEqual(word1, word2));
    }
}
