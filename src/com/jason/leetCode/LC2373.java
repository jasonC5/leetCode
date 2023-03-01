package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/3/1 9:04:24
 * @description
 */
public class LC2373 {
    public static final int[][] POINT = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length, max;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                max = 0;
                for (int[] p : POINT) {
                    max = Math.max(max, grid[i + p[0]][j + p[1]]);
                }
                ans[i - 1][j - 1] = max;
            }
        }
        return ans;
    }
}
