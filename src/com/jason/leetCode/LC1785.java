package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/16 9:47:44
 * @description
 */
public class LC1785 {
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        long a = Math.abs(sum - goal);
        return (int) ((a + limit - 1) / limit);
    }
}
