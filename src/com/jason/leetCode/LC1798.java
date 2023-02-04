package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2023/2/4 21:03:28
 * @description
 */
public class LC1798 {
    public int getMaximumConsecutive(int[] coins) {
        int ans = 1;
        Arrays.sort(coins);
        for (int coin : coins) {
            if (coin > ans) {
                break;
            }
            ans += coin;
        }
        return ans;
    }
}
