package com.jason.leetCode;

public class LC688 {
    public static int[] POSITION = {1, 2, -1, 2, 1, -2, -1, -2, 1};

    public double knightProbability(int n, int k, int row, int column) {
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }
        double[][][] dp = new double[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int step = 1; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int l = 0; l < 8; l++) {
                        int preI = i + POSITION[l];
                        int preJ = j + POSITION[l + 1];
                        if (preI >= 0 && preI < n && preJ >= 0 && preJ < n) {
                            dp[i][j][step] += dp[preI][preJ][step - 1] / 8;
                        }
                    }
                }
            }
        }
        return dp[row][column][k];
    }

    public static void main(String[] args) {
        int n = 3, k = 2, row = 0, column = 0;
        System.out.println(new LC688().knightProbability(n, k, row, column));
    }
}
