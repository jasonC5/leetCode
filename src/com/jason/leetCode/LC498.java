package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/6/14 9:06:08
 * @description
 */
public class LC498 {
    public static int[] findDiagonalOrder(int[][] mat) {
        // 正向：向上，向右
        // 反向：向下，向左
        boolean up = true;
        int m = mat.length;
        int n = mat[0].length;
        int x = 0, y = 0;
        int[] ans = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            ans[i] = mat[x][y];
            if (up) {// 往右上方向走
                if (x - 1 >= 0 && y + 1 < n) {
                    x -= 1;
                    y += 1;
                } else {// 转向 往右或者往下走
                    up = false;
                    if (y + 1 < n) {
                        y += 1;
                    } else {
                        x += 1;
                    }
                }
            } else {// 往左下方走
                if (x + 1 < m && y - 1 >= 0) {
                    x += 1;
                    y -= 1;
                } else {// 转向 往右或者往下走
                    up = true;
                    if (x + 1 < m) {
                        x += 1;
                    } else {
                        y += 1;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = new int[][]{{1, 2, 3}};
        System.out.println(Arrays.toString(findDiagonalOrder(matrix)));
    }
}
