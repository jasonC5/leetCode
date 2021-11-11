package com.jason.leetCode;

public class LC375 {
    public static int getMoneyAmount0(int n) {
        return process(1, n);
    }

    private static int process(int start, int end) {
        if (start >= end) {
            return 0;
        }
        //猜
        int ans = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int cost = i/*本次花费*/ + Math.max(process(start, i - 1), process(i + 1, end));// 依赖左下
            ans = Math.min(ans, cost);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));
    }

    public static int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int start = n - 1; start >= 1; start--) {
            for (int end = start + 1; end <= n; end++) {
                dp[start][end] = Integer.MAX_VALUE;
                for (int k = start; k < end; k++) {
                    int cost = k/*本次花费*/ + Math.max(dp[start][k - 1], dp[k + 1][end]);// 依赖左下
                    dp[start][end] = Math.min(dp[start][end], cost);
                }
            }
        }
        return dp[1][n];
    }
}
