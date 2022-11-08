package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/11/8 9:16:11
 * @description
 */
public class LC1684 {
    public static int countConsistentStrings(String allowed, String[] words) {
        int delegate = 0;
        for (int i = 0; i < allowed.length(); i++) {
            delegate |= 1 << (allowed.charAt(i) - 'a');
        }
        int ans = 0;
        retry:
        for (String word : words) {
            char[] chars = word.toCharArray();
            for (char c : chars) {
                if (((1 << (c - 'a')) & delegate) == 0) {
                    continue retry;
                }
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String allowed = "ab";
        String[] words = {"ad", "bd", "aaab", "baa", "badab"};
        System.out.println(countConsistentStrings(allowed, words));
    }
}
