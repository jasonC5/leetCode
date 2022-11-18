package com.jason.leetCode;

import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/11/18 9:24:18
 * @description
 */
public class LC891 {
    public static int MOD = 1000000007;

    public static int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long preSum = nums[0], fac = 2, ans = 0;
        for (int i = 1; i < n; i++) {
            // 当前为最大值，和之前每个位置的宽度之和
            long cur = ((nums[i] * (fac - 1) - preSum) + MOD) % MOD;
            ans = (ans + cur) % MOD;
            preSum = ((preSum << 1) + nums[i]) % MOD;
            fac = (fac << 1) % MOD;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 3};
        System.out.println(sumSubseqWidths(arr));
    }
}
