package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/5/25 8:28:34
 * @description
 */
public class LC467 {
    public int findSubstringInWraproundString(String p) {
        //以某个字母结束的
        int[] endLen = new int[26];
        int max = 0;
        for (int i = 0; i < p.length(); i++) {
            max = i > 0 && ((p.charAt(i) - p.charAt(i - 1) + 26) % 26 == 1) ? max + 1 : 1;
            int idx = p.charAt(i) - 'a';
            endLen[idx] = Math.max(endLen[idx], max);
        }
        return Arrays.stream(endLen).sum();
    }
}
