package com.jason.leetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenjieaj
 * @date 2023/6/6 9:27:49
 * @description
 */
public class LC2352 {
    public static int equalPairs(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(grid[i][j]);
                sb.append(" ");
            }
            String arr = sb.toString();
            count.put(arr, count.getOrDefault(arr, 0) + 1);
        }
        for (int j = 0; j < n; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(grid[i][j]);
                sb.append(" ");
            }
            String arr = sb.toString();
            ans += count.getOrDefault(arr, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        System.out.println(equalPairs(grid));
    }
}
