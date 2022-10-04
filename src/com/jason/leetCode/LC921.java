package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/4 22:23:29
 * @description
 */
public class LC921 {
    public int minAddToMakeValid(String s) {
        int n = s.length();
        int left = 0, added = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    added++;
                }
            }
        }
        return left + added;
    }
}
