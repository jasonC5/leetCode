package com.jason.leetCode;

/**
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 * <p>
 * 输入：matrix =
 * [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author JasonC5
 */
public class LC1277 {

    public static int countSquares(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] leftCount = new int[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            leftCount[i][0] = matrix[i][0] == 1 ? 1 : 0;
            count += matrix[i][0] == 1 ? 1 : 0;//在第一格，以此为右下角的子矩阵只有自己
            for (int j = 1; j < col; j++) {
                leftCount[i][j] = matrix[i][j] == 1 ? leftCount[i][j - 1] + 1 : 0;
                //以当前点作为右下角，有几个子矩阵全1
                count += maxSonMatrix(leftCount, i, j);
            }
        }
        return count;
    }

    private static int maxSonMatrix(int[][] leftCount, int i, int j) {
        if (leftCount[i][j] == 0 || leftCount[i][j] == 1) {
            return leftCount[i][j];
        } else if (i == 0 || j == 0) {
            return 1;
        }
        int min = leftCount[i][j];
        int count = 1;
        for (int k = i - 1; k >= 0; k--) {
            min = Math.min(min, leftCount[k][j]);
            if (min < count + 1) {
                break;
            } else {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        System.out.println(countSquares(matrix));
        return;
    }
}
