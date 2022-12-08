package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/8 9:32:38
 * @description
 */
public class LC1821 {
    public boolean squareIsWhite(String coordinates) {
        return ((coordinates.charAt(0) - 'a' + coordinates.charAt(1) - '0') & 1) == 0;
    }
}
