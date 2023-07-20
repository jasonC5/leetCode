package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/7/20 9:09:06
 * @description
 */
public class LC918 {
    public static int maxSubarraySumCircular(int[] nums) {
        int dpMin = 0, min = Integer.MAX_VALUE, dpMax = 0, max = Integer.MIN_VALUE, sum = 0;
        for (int num : nums) {
            sum += num;
            dpMin = Math.min(dpMin + num, num);
            dpMax = Math.max(dpMax + num, num);
            min = Math.min(min, dpMin);
            max = Math.max(max, dpMax);
        }
        return max > 0 ? Math.max(max, sum - min) : max;
    }

    public static void main(String[] args) {
        int[] arr = {-3, -2, -3};
        System.out.println(maxSubarraySumCircular(arr));
    }
}
