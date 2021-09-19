package com.jason.leetCode;

public class LC650 {
    public static int minSteps(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = i / 2; j >= 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i / j);
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(minSteps(100));
        System.out.println(minSteps2(100));
    }

    public static int minSteps2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + (i / j));
                    dp[i] = Math.min(dp[i], dp[i / j] + j);
                }
            }
        }
        return dp[n];
    }
}
