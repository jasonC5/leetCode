package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC2312 {
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] mn = new long[m + 1][n + 1];
        long[][] cache = new long[m + 1][n + 1];
        for (int[] price : prices) {
            mn[price[0]][price[1]] = price[2];
        }
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                cache[i][j] = -1;
            }
        }
        return process(m, n, mn, cache);
    }

    private long process(int m, int n, long[][] mn, long[][] cache) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (cache[m][n] != -1) {
            return cache[m][n];
        }
        long ans = mn[m][n];
        for (int i = 1; i < m; i++) {
            ans = Math.max(ans, process(i, n, mn, cache) + process(m - i, n, mn, cache));
        }
        for (int j = 1; j < n; j++) {
            ans = Math.max(ans, process(m, j, mn, cache) + process(m, n - j, mn, cache));
        }
        cache[m][n] = ans;
        return ans;
    }


    public long sellingWood2(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];
        for (int[] p : prices) {
            dp[p[0]][p[1]] = p[2];
        }
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 1; k <= (i >> 1); k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k <= (j >> 1); k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j-k]);
                }
            }
        }
        return dp[m][n];
    }
}
