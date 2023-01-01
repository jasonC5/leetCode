package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/1 16:40:12
 * @description
 */
public class LC2351 {
    public char repeatedCharacter(String s) {
        int n = s.length(), mark = 0;
        for (int i = 0; i < n; i++) {
            int seek = s.charAt(i) - 'a';
            if (((mark >> seek) & 1) == 0) {
                mark |= 1 << seek;
            } else {
                return s.charAt(i);
            }
        }
        return 1;
    }
}
