package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/9/24 21:19:02
 * @description
 */
public class LC1652 {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = 1; j <= Math.abs(k); j++) {
                if (k > 0) {
                    cur += code[(i + j) % n];
                } else if (k < 0) {
                    cur += code[(i - j + n) % n];
                }
            }
            ans[i] = cur;
        }
        return ans;
    }
}
