package com.jason.leetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjieaj
 * @date 2022/7/20 9:16:47
 * @description
 */
public class LC1260 {
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] target = new int[m][n];
        for (int j = 0; j < n; j++) {
            // 最终结果的每一列，对应原始的多少列
            int col = (j - (k % n) + n) % n;
            // 这一列往下移动了多少格
            int down = (k / n + (col > k % n ? 1 : 0)) % m;
            for (int i = 0; i < m; i++) {
                target[i][j] = grid[(i - down + m) % m][col];
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(target[i][j]);
            }
            ans.add(list);
        }
        return ans;
    }


    public List<List<Integer>> shiftGrid0(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            ret.add(row);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index1 = (i * n + j + k) % (m * n);
                ret.get(index1 / n).set(index1 % n, grid[i][j]);
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(shiftGrid(grid, 1));
    }
}
