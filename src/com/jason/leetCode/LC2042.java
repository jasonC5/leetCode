package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/3 9:30:53
 * @description
 */
public class LC2042 {
    public boolean areNumbersAscending(String s) {
        String[] tokens = s.split(" ");
        int pre = -1;
        for (String token : tokens) {
            if (token.charAt(0) >= '0' && token.charAt(0) <= '9') {
                int num = Integer.parseInt(token);
                if (num > pre) {
                    pre = num;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
