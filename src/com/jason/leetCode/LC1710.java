package com.jason.leetCode;

import java.util.Arrays;

/**
 * @author chenjieaj
 * @date 2022/11/15 9:01:00
 * @description
 */
public class LC1710 {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int ans = 0, i = 0, n = boxTypes.length;
        Arrays.sort(boxTypes, (i1, i2) -> i2[1] - i1[1]);
        while (truckSize > 0 && i < n) {
            int num = Math.min(truckSize, boxTypes[i][0]);
            truckSize -= num;
            ans += boxTypes[i++][1] * num;
        }
        return ans;
    }
}
