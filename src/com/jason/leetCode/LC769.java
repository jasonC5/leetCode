package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/10/13 8:58:38
 * @description
 */
public class LC769 {
    public static int maxChunksToSorted(int[] arr) {
        int max = 0, ans = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            if (i == max) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxChunksToSorted(new int[]{1, 0, 2, 3, 4}));
    }
}
