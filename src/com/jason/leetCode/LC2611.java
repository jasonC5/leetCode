package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/6/7 21:41:34
 * @description
 */
public class LC2611 {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        int[] diffs = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            diffs[i] = reward1[i] - reward2[i];
            ans += reward2[i];
        }
        Arrays.sort(diffs);
        for (int i = 0; i < k; i++) {
            ans += diffs[n - 1 - i];
        }
        return ans;
    }
}
