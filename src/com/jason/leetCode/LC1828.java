package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2023/1/24 16:53:57
 * @description
 */
public class LC1828 {
    public int[] countPoints(int[][] points, int[][] queries) {
        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int c = 0, x = queries[i][0], y = queries[i][1], r = queries[i][2];
            for (int[] point : points) {
                if ((x - point[0]) * (x - point[0]) + (y - point[1]) * (y - point[1]) <= r * r) {
                    c++;
                }
            }
            ans[i] = c;
        }
        return ans;
    }
}
