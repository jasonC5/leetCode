package com.jason.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC1020 {
    public static final int[] location = new int[]{-1, 0, 1, 0, -1};
    // 超时
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) {
                queue.add(new int[]{0, i});
                grid[0][i] = 0;
            }
            if (grid[m - 1][i] == 1) {
                queue.add(new int[]{m - 1, i});
                grid[m - 1][i] = 0;
            }
        }
        for (int i = 1; i < m - 1; i++) {
            if (grid[i][0] == 1) {
                queue.add(new int[]{i, 0});
                grid[i][0] = 0;
            }
            if (grid[i][n - 1] == 1) {
                queue.add(new int[]{i, n - 1});
                grid[i][n - 1] = 0;
            }
        }
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                if (poll[0] + location[i] >= 0 && poll[0] + location[i] < m && poll[1] + location[i + 1] >= 0 && poll[1] + location[i + 1] < n
                        && grid[poll[0] + location[i]][poll[1] + location[i + 1]] == 1) {
                    grid[poll[0] + location[i]][poll[1] + location[i + 1]] = 0;
                    queue.add(new int[]{poll[0] + location[i], poll[1] + location[i + 1]});
                }
            }
        }
        int ans = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                ans += grid[i][j];
            }
        }
        return ans;
    }
}
