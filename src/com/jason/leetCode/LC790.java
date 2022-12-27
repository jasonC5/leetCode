package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC790 {
    public int numTilings(int n) {
        int[] dp = new int[n > 2 ? (n + 1) : 4];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i] + dp[i - 1] * 2) % 1000000007;
            dp[i] = (dp[i] +dp[i - 3]) % 1000000007;
        }
        return dp[n];
    }
}
