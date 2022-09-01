package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/9/1 9:05:20
 * @description
 */
public class LC1475 {
    // 单调栈
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] stack = new int[n];
        int idx = 0;
        int[] ans = new int[n];
        for (int i = 0; i < prices.length; i++) {
            while (idx > 0 && prices[stack[idx - 1]] >= prices[i]) {
                int preIdx = stack[--idx];
                ans[preIdx] = prices[preIdx] - prices[i];
            }
            stack[idx++] = i;
        }
        while (idx > 0) {
            int cur = stack[--idx];
            ans[cur] = prices[cur];
        }
        return ans;
    }
}
