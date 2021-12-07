package com.jason.leetCode;

import java.util.Arrays;

/**
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * <p>
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 * <p>
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 * <p>
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 * 示例 2：
 * <p>
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * 输出：[[1,3,3],[2,3,3]]
 * 示例 3：
 * <p>
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * 输出：[[2,2,2],[2,1,2],[2,2,2]]
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coloring-a-border
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1034 {
    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] marker = new boolean[m][n];
        boolean[][] visited = new boolean[m][n];
        int oldColor = grid[row][col];
        // bfs找边界
        dfs(grid, row, col, oldColor, marker, visited);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (marker[i][j]) {
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }

    public static final int[] DIRECTION = {-1, 0, 1, 0, -1};

    private static void dfs(int[][] grid, int row, int col, int color, boolean[][] marker, boolean[][] visited) {
        visited[row][col] = true;
        if (row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1) {// 边界
            marker[row][col] = true;
        }
        boolean isBorder = false;  //4个方向都和自己一样，说明不是边界，只要有一个不一样，说明是边界
        for (int i = 0; i < 4; i++) {// 遍历，上下左右
            if (row + DIRECTION[i] >= 0 && row + DIRECTION[i] < grid.length && col + DIRECTION[i + 1] >= 0 && col + DIRECTION[i + 1] < grid[0].length // 没有越界
                    && !visited[row + DIRECTION[i]][col + DIRECTION[i + 1]]) {//没有访问过
                if (grid[row + DIRECTION[i]][col + DIRECTION[i + 1]] == color) {// 深度优先，往下遍历
                    dfs(grid, row + DIRECTION[i], col + DIRECTION[i + 1], color, marker, visited);
                } else {
                    isBorder = true;
                }
            }
        }
        if (isBorder) {
            marker[row][col] = true;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 1}, {1, 2, 2}, {2, 2, 1}};
        int row = 1, col = 1, color = 2;
        int[][] ints = colorBorder(grid, row, col, color);
        System.out.println(Arrays.deepToString(ints));
    }
}
