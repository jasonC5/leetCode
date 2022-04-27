package com.jason.leetCode;

import java.util.*;

/**
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * <p>
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 * <p>
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *  
 * <p>
 * 提示：
 * <p>
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pacific-atlantic-water-flow
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Administrator
 */
public class LC417 {
    public static final int[] LOCATION = {0, 1, 0, -1, 0};

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dp = new int[m][n];
        Queue<int[]> queue1 = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            dp[0][i] |= 1;// 上方可流入太平洋
            queue1.add(new int[]{0, i});
            dp[m - 1][i] |= 2;// 下方可流入大西洋
            queue2.add(new int[]{m - 1, i});
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] |= 1;// 左方可流入太平洋
            queue1.add(new int[]{i, 0});
            dp[i][n - 1] |= 2;// 右方可流入大西洋
            queue2.add(new int[]{i, n - 1});
        }
        // 可流入太平洋：从左下往右下推//TODO 不能直接左上或者右下往下推dp，改为DFS
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                dp[i][j] |= heights[i][j] >= heights[i - 1][j] ? dp[i - 1][j] : 0;
//                dp[i][j] |= heights[i][j] >= heights[i][j - 1] ? dp[i][j - 1] : 0;
//            }
//        }

        while (!queue1.isEmpty()) {
            int[] poll = queue1.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = poll[0] + LOCATION[i];
                int nextJ = poll[1] + LOCATION[i + 1];
                if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && heights[nextI][nextJ] >= heights[poll[0]][poll[1]] && (dp[nextI][nextJ] & 1) == 0) {
                    dp[nextI][nextJ] |= 1;
                    queue1.add(new int[]{nextI, nextJ});
                }
            }
        }
//        // 可流入大西洋：从右下往左上推
//        for (int i = m - 2; i >= 0; i--) {
//            for (int j = n - 2; j >= 0; j--) {
//                dp[i][j] |= heights[i][j] >= heights[i + 1][j] ? dp[i + 1][j] : 0;
//                dp[i][j] |= heights[i][j] >= heights[i][j + 1] ? dp[i][j + 1] : 0;
//            }
//        }
        while (!queue2.isEmpty()) {
            int[] poll = queue2.poll();
            for (int i = 0; i < 4; i++) {
                int nextI = poll[0] + LOCATION[i];
                int nextJ = poll[1] + LOCATION[i + 1];
                if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && heights[nextI][nextJ] >= heights[poll[0]][poll[1]] && (dp[nextI][nextJ] & 2) == 0) {
                    dp[nextI][nextJ] |= 2;
                    queue2.add(new int[]{nextI, nextJ});
                }
            }
        }
        // 收答案
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 3) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(i);
                    cur.add(j);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(pacificAtlantic(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}));
        System.out.println(pacificAtlantic(new int[][]{{1, 1}, {1, 1}, {1, 1}}));
    }

}
