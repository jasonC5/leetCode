package com.jason.leetCode;

import java.util.Arrays;

public class LC1996 {
    public static int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (i1, i2) -> i2[0] == i1[0] ? (i1[1] - i2[1]) : (i2[0] - i1[0]));
        int len = properties.length;
        int max = properties[0][1];
        int ans = 0;
        for (int i = 1; i < len; i++) {
            if (properties[i][1] < max) {
                ans++;
            } else {
                max = properties[i][1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] nums = {{5, 5}, {6, 3}, {3, 6}};
//        int[][] nums = {{2,2},  {3, 3}};
//        int[][] nums = {{1, 5}, {10, 4}, {4, 3}};
        int[][] nums = {{1, 1}, {2, 1}, {2, 2}, {1, 2}};
        System.out.println(numberOfWeakCharacters(nums));
    }
}
