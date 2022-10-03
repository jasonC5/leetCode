package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/3 9:37:31
 * @description
 */
public class LC1784 {
    public boolean checkOnesSegment(String s) {
        int n = s.length();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    return false;
                }
                flag = true;
            }
        }
        return flag;
    }
}
