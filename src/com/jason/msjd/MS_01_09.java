package com.jason.msjd;

/**
 * @author chenjieaj
 * @date 2022/9/29 9:08:07
 * @description
 */
public class MS_01_09 {
    public boolean isFlipedString(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m != n) {
            return false;
        } else if (m == 0) {
            return true;
        }
        retry:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1.charAt(j) != s2.charAt((i + j) % n)) {
                    continue retry;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isFlipedString1(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        return (s2 + s2).contains(s1);
    }
}
