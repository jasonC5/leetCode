package com.jason.offer;

public class OfferII_091 {
    public int minCost(int[][] costs) {
        int n = costs.length;
//        int[][] dp = new int[n][3];
        // 空间压缩
        int[] dpPre = new int[3];
        int[] dpCur = new int[3];
        dpPre[0] = costs[0][0];
        dpPre[1] = costs[0][1];
        dpPre[2] = costs[0][2];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int min = Integer.MAX_VALUE;
//                for (int k = 0; k < 3; k++) {
//                    if (k != j) {
//                        min = Math.min(min, dp[i - 1][k] + costs[i][j]);
//                    }
//                }
                min = Math.min(min, dpPre[(j + 1) % 3] + costs[i][j]);
                min = Math.min(min, dpPre[(j + 2) % 3] + costs[i][j]);
                dpCur[j] = min;
            }
            System.arraycopy(dpCur, 0, dpPre, 0, 3);
        }
        return Math.min(dpPre[0], Math.min(dpPre[1], dpPre[2]));
    }
}
