package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/11 22:02:05
 * @description
 */
public class LC1827 {
    public int minOperations(int[] nums) {
        int next = nums[0] + 1, n = nums.length, ans = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= next) {
                next = nums[i] + 1;
            } else {
                ans += next++ - nums[i];
            }
        }
        return ans;
    }
}
