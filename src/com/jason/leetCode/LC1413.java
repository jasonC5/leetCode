package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/9 9:06:55
 * @description
 */
public class LC1413 {
    public int minStartValue(int[] nums) {
        int preSum = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            preSum += num;
            min = Math.min(min, preSum);
        }
        return Math.max(1, 1 - min);
    }
}
