package com.jason.leetCode;

import java.util.Arrays;

public class LC661 {
    public static int[][] LOCATION = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0, num = 0;
                for (int[] ints : LOCATION) {
                    if (ints[0] + i >= 0 && ints[0] + i < m && ints[1] + j >= 0 && ints[1] + j < n) {
                        num++;
                        sum += img[ints[0] + i][ints[1] + j];
                    }
                }
                ans[i][j] = sum / num;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] img = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println(Arrays.deepToString(imageSmoother(img)));
    }
}
