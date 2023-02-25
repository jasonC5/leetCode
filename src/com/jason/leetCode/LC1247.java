package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/2/25 13:02:34
 * @description
 */
public class LC1247 {
    public int minimumSwap(String s1, String s2) {
        int p1 = 0, p2 = 0;
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 == 'x' && c2 == 'y') {
                p1++;
            } else if (c1 == 'y' && c2 == 'x') {
                p2++;
            }
        }
        if (((p1 + p2) & 1) == 1) {
            return -1;
        }
        return (p1 >> 1) + (p2 >> 1) + (p1 & 1) + (p2 & 1);
    }
}
