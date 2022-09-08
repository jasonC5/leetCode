package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/9/8 9:12:52
 * @description
 */
public class LC667 {
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 1; i < n - k; i++) {
            ans[i - 1] = i;
        }
        int idx = n - k - 1;
        for (int i = n - k, j = n; i <= j; i++, j--) {
            ans[idx++] = i;
            if (i != j) {
                ans[idx++] = j;
            }
        }
        return ans;
    }
}
