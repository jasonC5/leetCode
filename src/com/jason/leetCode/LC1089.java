package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author JasonC5
 */
public class LC1089 {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int[] tmp = new int[n];
        int idx = 0;
        System.arraycopy(arr, 0, tmp, 0, n);
        Arrays.fill(arr, 0);
        for (int i = 0; i < n; i++) {
            arr[i] = tmp[idx];
            if (tmp[idx] == 0) {
                i++;
            }
            idx++;
        }
    }
}
