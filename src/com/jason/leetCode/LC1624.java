package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/9/19 9:23:51
 * @description
 */
public class LC1624 {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] idxArr = new int[26];
        int ans = -1;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (idxArr[c] == 0) {
                idxArr[c] = i + 1;
            } else {
                ans = Math.max(ans, i - idxArr[c]);
            }
        }
        return ans;
    }
}
