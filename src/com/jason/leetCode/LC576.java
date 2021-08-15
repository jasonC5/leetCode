package com.jason.leetCode;

/**
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 * <p>
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * 输出：6
 * 示例 2：
 * <p>
 * <p>
 * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * 输出：12
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/out-of-boundary-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC576 {

    public static void main(String[] args) {
//        System.out.println(findPaths(2, 2, 2, 0, 0));
//        System.out.println(findPaths2(2, 2, 2, 0, 0));
//        System.out.println(findPaths(1, 3, 3, 0, 1));
//        System.out.println(findPaths2(1, 3, 3, 0, 1));
        System.out.println(findPaths(2, 3, 8, 1, 0));
        System.out.println(findPaths2(2, 3, 8, 1, 0));
    }

    //鲍勃死亡问题变种

    /**
     * @param m           行数row
     * @param n           列数col
     * @param maxMove     移动次数
     * @param startRow    起始位置行数
     * @param startColumn 起始位置列数
     * @return
     */
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        //三维动态规划
//        int[][][] dp = new int[maxMove + 1][m][n];
//        if () {
//
//        }
//
//        return dp[maxMove][startRow][startColumn];
        return process(m, n, maxMove, startRow, startColumn);
    }

    public static final int MOD = 1000000007;

    /**
     * @param row
     * @param col
     * @param step
     * @param curRow
     * @param curCol
     * @return
     */
    private static int process(int row, int col, int step, int curRow, int curCol) {
        if (curRow == -1 || curRow == row) {
            return 1;
        }
        if (curCol == -1 || curCol == col) {
            return 1;
        }
        if (step == 0) {
            return 0;
        }
        int ans = 0;
        //上
        ans = (process(row, col, step - 1, curRow - 1, curCol) % MOD + ans) % MOD;
        //下
        ans = (process(row, col, step - 1, curRow + 1, curCol) % MOD + ans) % MOD;
        //左
        ans = (process(row, col, step - 1, curRow, curCol - 1) % MOD + ans) % MOD;
        //右
        ans = (process(row, col, step - 1, curRow, curCol + 1) % MOD + ans) % MOD;
        return ans;
    }


    //记忆化搜索
    public static int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[maxMove + 1][m][n];
        for (int i = 0; i <= maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return process(m, n, maxMove, startRow, startColumn, dp);
    }

    private static int process(int row, int col, int step, int curRow, int curCol, int[][][] dp) {
        if (curRow == -1 || curRow == row) {
            return 1;
        }
        if (curCol == -1 || curCol == col) {
            return 1;
        }
        if (step == 0) {
            return 0;
        }
        if (dp[step][curRow][curCol] != -1) {
            return dp[step][curRow][curCol];
        }
        int ans = 0;
        //上
        ans = (process(row, col, step - 1, curRow - 1, curCol, dp) % MOD + ans) % MOD;
        //下
        ans = (process(row, col, step - 1, curRow + 1, curCol, dp) % MOD + ans) % MOD;
        //左
        ans = (process(row, col, step - 1, curRow, curCol - 1, dp) % MOD + ans) % MOD;
        //右
        ans = (process(row, col, step - 1, curRow, curCol + 1, dp) % MOD + ans) % MOD;
        dp[step][curRow][curCol] = ans;
        return ans;
    }

}
