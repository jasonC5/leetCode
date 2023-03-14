package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/3/14 9:47:33
 * @description
 */
public class LC1605 {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length, i = 0, j = 0, v;
        int[][] matrix = new int[m][n];
        while (i < m && j < n) {
            v = Math.min(rowSum[i], colSum[j]);
            matrix[i][j] = v;
            rowSum[i] -= v;
            colSum[j] -= v;
            if (rowSum[i] == 0) {
                i++;
            }
            if (colSum[j] == 0) {
                j++;
            }
        }
        return matrix;
    }
}
