package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/5/19 9:01:55
 * @description
 */
public class LC462 {
    public int minMoves2(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int midVal = nums[length >> 1];
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += Math.abs(nums[i] - midVal);
        }
        return ans;
    }
}
