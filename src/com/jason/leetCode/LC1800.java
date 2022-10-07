package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/7 8:37:31
 * @description
 */
public class LC1800 {
    public int maxAscendingSum(int[] nums) {
        int ans = 0, cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                cur += nums[i];
            } else {
                ans = Math.max(ans, cur);
                cur = nums[i];
            }
        }
        ans = Math.max(ans, cur);
        return ans;
    }
}
