package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/12/10 17:27:34
 * @description
 */
public class LC1691 {
    public int maxHeight(int[][] cuboids) {
        // 从小到大，把最大的一面当高
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (c1, c2) -> (c1[0] + c1[1] + c1[2]) - (c2[0] + c2[1] + c2[2]));
        int n = cuboids.length, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[i][0] >= cuboids[j][0]
                        && cuboids[i][1] >= cuboids[j][1]
                        && cuboids[i][2] >= cuboids[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
