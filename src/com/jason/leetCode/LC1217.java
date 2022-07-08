package com.jason.leetCode;

/**
 * @author chenjieaj
 * @date 2022/7/8 9:16:56
 * @description
 */
public class LC1217 {
    public int minCostToMoveChips(int[] position) {
        int odd = 0, even = 0;
        for (int num : position) {
            odd += num & 1;
            even += 1 - (num & 1);
        }
        return Math.min(odd, even);
    }
}
