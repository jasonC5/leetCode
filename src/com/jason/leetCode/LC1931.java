package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/10/3 9:51:57
 * @description
 */
public class LC1931 {
    public static final int MOD = 1000000007;

    public static int colorTheGrid(int m, int n) {
        // m <=5 n<=1000  调整一下
        int[][][] dp = new int[n][m][1 << (m << 1)];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process(0, 0, 0, n, m, dp);
    }

    private static int process(int i, int j, int status, int m, int n, int[][][] dp) {
        if (i == m) {
            return 1;
        }
        if (j == n) {
            return process(i + 1, 0, status, m, n, dp);
        }
        if (dp[i][j][status] != -1) {
            return dp[i][j][status];
        }
        int ans = 0;
        // 只判断上面和左边的颜色
        int up = (status >> (j << 1)) & 3;
        int left = j == 0 ? 0 : ((status >> ((j - 1) << 1)) & 3);
        if (up != 1 && left != 1) {
//            ans += process(i, j + 1, (status | (~(3 << (j << 1)))) | (1 << (j << 1)), m, n, dp);
            ans += process(i, j + 1, (status ^ (up << (j << 1))) | (1 << (j << 1)), m, n, dp);
            ans %= MOD;
        }
        if (up != 2 && left != 2) {
//            ans += process(i, j + 1, (status | (~(3 << (j << 1)))) | (2 << (j << 1)), m, n, dp);
            ans += process(i, j + 1, (status ^ (up << (j << 1))) | (2 << (j << 1)), m, n, dp);
            ans %= MOD;
        }
        if (up != 3 && left != 3) {
//            ans += process(i, j + 1, (status | (~(3 << (j << 1)))) | (3 << (j << 1)), m, n, dp);
            ans += process(i, j + 1, (status ^ (up << (j << 1))) | (3 << (j << 1)), m, n, dp);
            ans %= MOD;
        }
        dp[i][j][status] = ans;
        return ans;
    }

    public static void main(String[] args) {
        int m = 2, n = 37;
        System.out.println(colorTheGrid(m, n));
    }
}
