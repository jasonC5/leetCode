package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/7/11 8:38:21
 * @description
 */
public class LC1911 {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] even = new long[n];
        long[] odd = new long[n];
        even[0] = nums[0];
        long ans = nums[0];
        for (int i = 1; i < n; i++) {
            even[i] = Math.max(even[i - 1], odd[i - 1] + nums[i]);
            odd[i] = Math.max(odd[i - 1], even[i - 1] - nums[i]);
            ans = Math.max(ans, Math.max(even[i], odd[i]));
        }
        return ans;
    }
}
