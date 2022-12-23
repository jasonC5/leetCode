package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/23 9:23:34
 * @description
 */
public class LC2011 {
    public int finalValueAfterOperations(String[] operations) {
        int x = 0;
        for (String operation : operations) {
            char[] chars = operation.toCharArray();
            for (char c : chars) {
                if (c == '+'){
                    x++;
                    break;
                } else if (c == '-') {
                    x--;
                    break;
                }
            }
        }
        return x;
    }
}
