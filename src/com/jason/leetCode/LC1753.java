package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/21 9:18:24
 * @description
 */
public class LC1753 {
    public int maximumScore(int a, int b, int c) {
        int sum = a + b + c, max = Math.max(a, Math.max(b, c));
        if (max > sum - max) {
            return sum - max;
        } else {
            return sum >> 1;
        }
    }
}
