package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/7/13 10:12:57
 * @description
 */
public class LC931 {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        System.arraycopy(matrix[0], 0, dp[0], 0, n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int pre = Integer.MAX_VALUE;
                for (int k = -1; k < 2; k++) {
                    if (j + k >= 0 && j + k < n) {
                        pre = Math.min(pre, dp[i - 1][j + k]);
                    }
                }
                dp[i][j] = pre + matrix[i][j];
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }
}
