package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/12/1 16:18:22
 * @description
 */
public class LC1779 {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int ans = -1, distance = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] != x && points[i][1] != y) {
                continue;
            }
            int cur = Math.abs(points[i][0] - x) + Math.abs(points[i][1] - y);
            if (cur < distance) {
                ans = i;
                distance = cur;
            }
        }
        return ans;
    }
}
