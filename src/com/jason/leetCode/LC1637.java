package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/3/30 9:41:12
 * @description
 */
public class LC1637 {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (i1, i2) -> i1[0] - i2[0]);
        int ans = 0;
        for (int i = 1; i < points.length; i++) {
            ans = Math.max(ans, points[i][0] - points[i - 1][0]);
        }
        return ans;
    }
}
