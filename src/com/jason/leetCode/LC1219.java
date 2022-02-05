package com.jason.leetCode;

public class LC1219 {

    public static final int[] location = new int[]{-1, 0, 1, 0, -1};
    int m, n;

    public int getMaximumGold(int[][] grid) {
        int ans = 0;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans = Math.max(ans, dfs(grid, i, j, new boolean[m][n]));
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i == -1 || i == m || j == -1 || j == n || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        int max = 0;
        for (int k = 0; k < 4; k++) {
            max = Math.max(max, dfs(grid, i + location[k], j + location[k + 1], visited));
        }
        visited[i][j] = false;
        return grid[i][j] + max;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
        System.out.println(new LC1219().getMaximumGold(grid));
    }
}
