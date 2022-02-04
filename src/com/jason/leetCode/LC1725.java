package com.jason.leetCode;

public class LC1725 {
    public int countGoodRectangles(int[][] rectangles) {
        int max = 0, num = 0;
        for (int[] rectangle : rectangles) {
            int len = Math.min(rectangle[0], rectangle[1]);
            if (len > max) {
                max = len;
                num = 1;
            } else if (len == max) {
                num++;
            }
        }
        return num;
    }
}
