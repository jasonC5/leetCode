package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/9 9:16:07
 * @description
 */
public class LC1708 {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n = n / 3;
        }
        return true;
    }
}
