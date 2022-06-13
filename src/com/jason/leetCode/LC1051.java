package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/6/13 9:32:11
 * @description
 */
public class LC1051 {
    public int heightChecker(int[] heights) {
        int length = heights.length;
        int[] sorted = new int[length];
        System.arraycopy(heights, 0, sorted, 0, length);
        Arrays.sort(sorted);
        int ans = 0;
        for (int i = 0; i < length; i++) {
            ans += (sorted[i] == heights[i] ? 0 : 1);
        }
        return ans;
    }
}
