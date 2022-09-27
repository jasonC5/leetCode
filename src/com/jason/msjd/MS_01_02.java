package com.jason.msjd;

/**
 * @author chenjieaj
 * @date 2022/9/27 8:47:59
 * @description
 */
public class MS_01_02 {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int c = s1.charAt(i) - 'a';
            cnt[c]++;
            c = s2.charAt(i) - 'a';
            cnt[c]--;
        }
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
