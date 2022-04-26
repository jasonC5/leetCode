package com.jason.leetCode;

public class LC883 {
    public int projectionArea(int[][] grid) {
        //三个平面投影的面积
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < grid.length; i++) {
            int bMax = 0, cMax = 0;
            for (int j = 0; j < grid[i].length; j++) {
                a += grid[i][j] == 0 ? 0 : 1;
                bMax = Math.max(bMax, grid[i][j]);
                // 由于是N*N的矩阵，所以可以直接这么写
                cMax = Math.max(cMax, grid[j][i]);
            }
            b += bMax;
            c += cMax;
        }
        return a + b + c;
    }
}
