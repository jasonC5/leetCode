package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/7/27 10:00:44
 * @description
 */
public class LC2500 {
    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            Arrays.sort(grid[i]);
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, grid[i][j]);
            }
            ans += max;
        }
        return ans;
    }
}
