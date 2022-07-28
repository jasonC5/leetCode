package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/7/28 9:01:25
 * @description
 */
public class LC1331 {
    public static int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int n = arr.length;
        int[][] container = new int[n][2];
        for (int i = 0; i < n; i++) {
            container[i][0] = arr[i];
            container[i][1] = i;
        }
        Arrays.sort(container, (c1, c2) -> c1[0] - c2[0]);
        int serial = 1;
        arr[container[0][1]] = 1;
        for (int i = 1; i < n; i++) {
            if (container[i][0] == container[i - 1][0]) {
                arr[container[i][1]] = serial;
            } else {
                arr[container[i][1]] = ++serial;
            }
        }
        return arr;
    }
}
