package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/8/26 9:03:44
 * @description
 */
public class LC1464 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return (nums[n - 1] - 1) * (nums[n - 2] - 1);
    }

    public int maxProduct2(int[] nums) {
        int n1 = 0, n2 = 0;
        for (int num : nums) {
            if (num > n1) {
                n2 = n1;
                n1 = num;
            } else if (num == n1) {
                n2 = n1;
            } else if (num > n2) {
                n2 = num;
            }
        }
        return (n1 - 1) * (n2 - 1);
    }
}
