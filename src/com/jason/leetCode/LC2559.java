package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/6/2 15:52:37
 * @description
 */
public class LC2559 {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] count = new int[n + 1];
        count[0] = 0;
        for (int i = 0; i < words.length; i++) {
            if (match(words[i])) {
                count[i + 1] = count[i] + 1;
            } else {
                count[i + 1] = count[i];
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0, li, ri; i < queries.length; i++) {
            li = queries[i][0];
            ri = queries[i][1];
            ans[i] = count[ri + 1] - count[li];
        }
        return ans;
    }

    private boolean match(String word) {
        char left = word.charAt(0);
        char right = word.charAt(word.length() - 1);
        return (left == 'a' || left == 'i' || left == 'u' || left == 'e' || left == 'o') &&
                (right == 'a' || right == 'i' || right == 'u' || right == 'e' || right == 'o');
    }
}
