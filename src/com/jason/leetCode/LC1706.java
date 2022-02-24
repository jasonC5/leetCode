package com.jason.leetCode;

import java.util.Arrays;

public class LC1706 {
    public static int[] findBall(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] ans = new int[col];
        for (int i = 0; i < col; i++) {
            ans[i] = i;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (ans[j] != -1) {
                    int pre = ans[j];
                    ans[j] += grid[i][ans[j]];
                    if (ans[j] == -1 || ans[j] == col) {
                        ans[j] = -1;
                    } else if (grid[i][pre] == 1 && pre < col - 1 && grid[i][pre + 1] == -1) {
                        ans[j] = -1;
                    } else if (grid[i][pre] == -1 && pre > 0 && grid[i][pre - 1] == 1) {
                        ans[j] = -1;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] grid = {
//                {1, 1, 1, -1, -1},
//                {1, 1, 1, -1, -1},
//                {-1, -1, -1, 1, 1},
//                {1, 1, 1, 1, -1},
//                {-1, -1, -1, -1, -1}
//        };
//        int[][] grid = {{-1}};
//        int[][] grid = {
//                {1, 1, 1, 1, 1, 1},
//                {-1, -1, -1, -1, -1, -1},
//                {1, 1, 1, 1, 1, 1},
//                {-1, -1, -1, -1, -1, -1}
//        };
        int[][] grid = {{1, -1, 1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 1, 1, 1, 1, -1, -1, 1, -1, 1, -1, 1, 1, 1, -1, -1, -1, 1, 1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1}
                , {-1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, 1, 1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, 1, 1, 1, 1, -1, 1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1}};
//        int[][] grid = {{1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}, {1, 1, 1, 1, 1, 1}, {-1, -1, -1, -1, -1, -1}};
        System.out.println(Arrays.toString(findBall(grid)));
    }
}
