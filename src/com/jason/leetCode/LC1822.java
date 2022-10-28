package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/27 9:10:37
 * @description
 */
public class LC1822 {
    public int arraySign(int[] nums) {
        int odd = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            } else if (num < 0) {
                odd++;
            }
        }
        return (odd & 1) == 0 ? 1 : -1;
    }
}
