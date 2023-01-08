package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/8 9:43:00
 * @description
 */
public class LC2185 {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String word : words) {
            ans += word.startsWith(pref) ? 1 : 0;
        }
        return ans;
    }
}
