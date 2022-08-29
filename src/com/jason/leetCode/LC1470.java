package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/8/29 9:01:15
 * @description
 */
public class LC1470 {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[n << 1];
        for (int i = 0; i < n; i++) {
            ans[i << 1] = nums[i];
            ans[(i << 1) + 1] = nums[n + i];
        }
        return ans;
    }
}
