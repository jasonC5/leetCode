package com.jason.leetCode;

/**
 * @author JasonC5
 */
public class LC768 {
    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] rightMin = new int[n];
        rightMin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(arr[i], rightMin[i + 1]);
        }
        int ans = 1;
        int leftMax = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            leftMax = Math.max(leftMax, arr[i]);
            if (leftMax <= rightMin[i + 1]) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(maxChunksToSorted(new int[]{5, 4, 3, 2, 1}));
    }
}
