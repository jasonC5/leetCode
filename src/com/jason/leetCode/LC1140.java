package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/2/22 9:12:06
 * @description
 */
public class LC1140 {
    public int stoneGameII(int[] piles) {
        int n = piles.length, postSum = 0;
        int[][] dp = new int[n][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            postSum += piles[i];
            for (int m = 1; m <= n; m++) {
                if (i + (m << 1) >= n) {
                    dp[i][m] = postSum;
                } else {
                    for (int x = 1; x <= (m << 1); x++) {
                        dp[i][m] = Math.max(dp[i][m], postSum - dp[i + x][Math.max(m, x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }
}
