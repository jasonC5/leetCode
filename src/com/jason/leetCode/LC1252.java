package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/7/12 9:13:31
 * @description
 */
public class LC1252 {
    public int oddCells(int m, int n, int[][] indices) {
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int[] index : indices) {
            rows[index[0]]++;
            cols[index[1]]++;
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += (rows[i] + cols[j]) & 1;
            }
        }
        return ans;
    }
}
