package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/7/4 8:52:59
 * @description
 */
public class LC2679 {
    public int matrixSum(int[][] nums) {
        int m = nums.length, n = nums[0].length, ans = 0;
        for (int[] num : nums) {
            Arrays.sort(num);
        }
        for (int col = n - 1; col >= 0; col--) {
            int max = 0;
            for (int row = 0; row < m; row++) {
                max = Math.max(max, nums[row][col]);
            }
            ans += max;
        }
        return ans;
    }
}
