package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/28 8:05:21
 * @description
 */
public class LC1664 {
    public int waysToMakeFair(int[] nums) {
        int n = nums.length, oddBefore = 0, evenBefore = 0, oddAfter = 0, evenAfter = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 1) {
                oddBefore += nums[i];
            } else {
                evenBefore += nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if ((i & 1) == 1) {
                oddBefore -= nums[i];
            } else {
                evenBefore -= nums[i];
            }
            if (oddBefore + evenAfter == oddAfter + evenBefore) {
                ans++;
            }
            if ((i & 1) == 1) {
                oddAfter += nums[i];
            } else {
                evenAfter += nums[i];
            }
        }
        return ans;
    }
}
