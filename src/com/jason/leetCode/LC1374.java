package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/1 8:58:24
 * @description
 */
public class LC1374 {
    public String generateTheString(int n) {
        if (n == 1) {
            return "a";
        }
        boolean odd = (n & 1) == 1;
        StringBuilder sb = new StringBuilder();
        if (odd) {
            for (int i = 0; i < n; i++) {
                sb.append('a');
            }
        } else {
            for (int i = 1; i < n; i++) {
                sb.append('a');
            }
            sb.append('b');
        }
        return sb.toString();
    }
}
