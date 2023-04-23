package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/4/23 9:19:45
 * @description
 */
public class LC1105 {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000000);
        dp[0] = 0;
        for (int i = 0, h, w; i < n; i++) {
            h = 0;// 本层高度
            w = 0;// 本层宽度
            for (int j = i; j >= 0; j--) {
                w += books[j][0];
                if (w > shelfWidth) {
                    break;
                }
                h = Math.max(h, books[j][1]);
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + h);
            }
        }
        return dp[n];
    }
}
