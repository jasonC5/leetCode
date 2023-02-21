package com.jason.leetCode;

import java.util.Arrays;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/2/21 15:12:43
 * @description
 */
public class LC1326 {
    public int minTaps(int n, int[] ranges) {
        // 从0~i最小需要多少个
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int[][] newRange = new int[n + 1][2];
        for (int i = 0; i < ranges.length; i++) {
            newRange[i] = new int[]{Math.max(0, i - ranges[i]), Math.min(n, i + ranges[i])};
        }
        Arrays.sort(newRange, (a1, a2) -> a1[0] - a2[0]);
        for (int[] range : newRange) {
            int l = range[0], r = range[1];
            if (dp[l] == Integer.MAX_VALUE) {
                return -1;
            }
            for (int i = l + 1; i <= r; i++) {
                dp[i] = Math.min(dp[i], 1 + dp[l]);
            }
        }
        return dp[n];
    }
}
