package com.jason.leetCode;

import java.util.Arrays;

public class LC1846 {

    public static void main(String[] args) {

    }

    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        return arr[length - 1];
    }
}
