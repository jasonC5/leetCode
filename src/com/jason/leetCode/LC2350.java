package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/8/9 16:07:20
 * @description
 */
public class LC2350 {
    public int shortestSequence(int[] rolls, int k) {
        boolean[] exist = new boolean[k];
        int existed = 0, ans = 1;
        for (int roll : rolls) {
            if (!exist[roll - 1]) {
                exist[roll - 1] = true;
                if (++existed == k) {
                    existed = 0;
                    Arrays.fill(exist, false);
                    ans++;
                }
            }
        }
        return ans;
    }
}
